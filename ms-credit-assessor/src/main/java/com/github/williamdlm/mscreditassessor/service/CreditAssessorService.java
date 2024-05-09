package com.github.williamdlm.mscreditassessor.service;

import com.github.williamdlm.mscreditassessor.exception.ClientDataNotFoundException;
import com.github.williamdlm.mscreditassessor.exception.ErrorComunicationWebserviceException;
import com.github.williamdlm.mscreditassessor.feignclient.CardFeignClient;
import com.github.williamdlm.mscreditassessor.feignclient.ClientFeignClient;
import com.github.williamdlm.mscreditassessor.pojo.*;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientFeignClient clientFeignClient;
    private final CardFeignClient cardFeignClient;

    public ClientStatus getClientStatus(String document) throws ClientDataNotFoundException, ErrorComunicationWebserviceException {
        try {
            //Obter dados do client - ms-client
            ResponseEntity<ClientData> clientDataResponseEntity = clientFeignClient.findByDocument(document);
            //Obter dados do cartao - ms-card
            ResponseEntity<List<ClientCard>> clientCardResponseEntity = cardFeignClient.findByDocument(document);
            return ClientStatus.builder()
                    .clientData(clientDataResponseEntity.getBody())
                    .cards(clientCardResponseEntity.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorComunicationWebserviceException(e.getMessage(), status);
        }
    }

    public ResponseEvaluateCredit evaluateCredit(String document, Long income) throws ClientDataNotFoundException, ErrorComunicationWebserviceException {
        try {
            ResponseEntity<ClientData> clientDataResponseEntity = clientFeignClient.findByDocument(document);
            ResponseEntity<List<Card>> cardsByIncome = cardFeignClient.findByIncomeLessThanEqual(income);

            List<Card> cardList = cardsByIncome.getBody();

            List<ClientCard> grantedCardList = cardList.stream().map(card -> {
                ClientData clientData = clientDataResponseEntity.getBody();

                BigDecimal limit = BigDecimal.valueOf(card.getCardLimit());
                BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal grantedLimit = factor.multiply(limit);

                ClientCard clientCard = new ClientCard();
                clientCard.setCardName(card.getName());
                clientCard.setCardBrand(card.getCardBrand());
                clientCard.setCardLimit(grantedLimit);
                return clientCard;
            }).collect(Collectors.toList());
            return new ResponseEvaluateCredit(grantedCardList);
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new ClientDataNotFoundException();
            }
            throw new ErrorComunicationWebserviceException(e.getMessage(), status);
        }
    }
}


