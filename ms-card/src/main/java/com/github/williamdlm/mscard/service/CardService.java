package com.github.williamdlm.mscard.service;

import com.github.williamdlm.mscard.dto.CardDTO;
import com.github.williamdlm.mscard.model.Card;
import com.github.williamdlm.mscard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    public Card saveCard(CardDTO cardDTO){
        Card newCard = cardDTO.toModel();
        return cardRepository.save(newCard);
    }

    public List<Card> getCardIncomeLessThanEqual(Long income) throws Exception {
        return cardRepository.findByIncomeLessThanEqual(income).orElseThrow(()-> new Exception("Card not found"));
    }

    public Card findCardById(Long cardId) throws Exception {
        return cardRepository.findById(cardId).orElseThrow(()->new Exception("Card not found"));
    }

    public List<Card> findAllCards(){
        return cardRepository.findAll();
    }
}
