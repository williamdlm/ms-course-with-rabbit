package com.github.williamdlm.mscreditassessor.service;

import com.github.williamdlm.mscreditassessor.exception.ClientDataNotFoundException;
import com.github.williamdlm.mscreditassessor.exception.ErrorComunicationWebserviceException;
import com.github.williamdlm.mscreditassessor.feignclient.CardFeignClient;
import com.github.williamdlm.mscreditassessor.feignclient.ClientFeignClient;
import com.github.williamdlm.mscreditassessor.pojo.ClientCard;
import com.github.williamdlm.mscreditassessor.pojo.ClientData;
import com.github.williamdlm.mscreditassessor.pojo.ClientStatus;
import feign.Client;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new ClientDataNotFoundException();
            }
            throw new ErrorComunicationWebserviceException(e.getMessage(),status);
        }
    }

}
