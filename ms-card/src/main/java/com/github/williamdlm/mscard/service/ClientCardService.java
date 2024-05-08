package com.github.williamdlm.mscard.service;

import com.github.williamdlm.mscard.model.ClientCard;
import com.github.williamdlm.mscard.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {
    private final ClientCardRepository clientCardRepository;

    public List<ClientCard> findByDocument(String document){
        List<ClientCard> clientCardList = clientCardRepository.findByDocument(document);
        return clientCardList;
    };
}
