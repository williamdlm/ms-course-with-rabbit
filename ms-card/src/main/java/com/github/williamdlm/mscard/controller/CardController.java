package com.github.williamdlm.mscard.controller;

import com.github.williamdlm.mscard.dto.CardDTO;
import com.github.williamdlm.mscard.model.Card;
import com.github.williamdlm.mscard.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping
    public ResponseEntity saveCard(@RequestBody CardDTO cardDTO){
        Card newCard = cardService.saveCard(cardDTO);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cardNumber={cardNumber}")
                .buildAndExpand(cardDTO.cardNumber())
                .toUri();
//        return ResponseEntity.status(HttpStatus.CREATED).build(); //Alternative way
        return ResponseEntity.created(headerLocation).build();

    }
    @GetMapping("/search")
    public ResponseEntity<List<Card>> findAllCards(){
        List<Card> cardList = cardService.findAllCards();
        return ResponseEntity.ok(cardList);
    }

    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> findByIncomeLessThanEqual(@RequestParam("income") Long income) throws Exception {
        List<Card>  cardList = cardService.getCardIncomeLessThanEqual(income);
        return ResponseEntity.ok(cardList);
    }

}
