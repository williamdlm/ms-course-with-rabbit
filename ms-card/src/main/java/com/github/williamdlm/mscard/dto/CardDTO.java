package com.github.williamdlm.mscard.dto;

import com.github.williamdlm.mscard.enums.CardBrand;
import com.github.williamdlm.mscard.model.Card;

public record CardDTO(String name, String cardNumber, Double income, Double cardLimit, CardBrand cardBrand) {
    public Card toModel() {
        return new Card(name, cardNumber, income, cardLimit, cardBrand);
    }
}
