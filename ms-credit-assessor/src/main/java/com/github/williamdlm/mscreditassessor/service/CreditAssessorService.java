package com.github.williamdlm.mscreditassessor.service;

import com.github.williamdlm.mscreditassessor.feignclient.CardFeignClient;
import com.github.williamdlm.mscreditassessor.feignclient.ClientFeignClient;
import com.github.williamdlm.mscreditassessor.pojo.ClientCard;
import com.github.williamdlm.mscreditassessor.pojo.ClientData;
import com.github.williamdlm.mscreditassessor.pojo.ClientStatus;
import feign.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAssessorService {

    private final ClientFeignClient clientFeignClient;
    private final CardFeignClient cardFeignClient;

    public ClientStatus getClientStatus(String document){
        //Obter dados do client - ms-client
        ResponseEntity<ClientData> clientDataResponseEntity = clientFeignClient.findByDocument(document);
        //Obter dados do cartao - ms-card
        ResponseEntity<List<ClientCard>> clientCardResponseEntity = cardFeignClient.findByDocument(document);
        return ClientStatus.builder()
                .clientData(clientDataResponseEntity.getBody())
                .cards(clientCardResponseEntity.getBody())
                .build();
    }

}
