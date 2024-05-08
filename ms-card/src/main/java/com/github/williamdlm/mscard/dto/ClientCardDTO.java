package com.github.williamdlm.mscard.dto;

import com.github.williamdlm.mscard.model.ClientCard;

import java.math.BigDecimal;

public record ClientCardDTO(String document, String cardBrand, BigDecimal income) {


    public static ClientCardDTO fromModel(ClientCard clientCard) {
        return new ClientCardDTO(clientCard.getDocument(), clientCard.getCard().getCardBrand().toString(), clientCard.getIncome());
    }

}
