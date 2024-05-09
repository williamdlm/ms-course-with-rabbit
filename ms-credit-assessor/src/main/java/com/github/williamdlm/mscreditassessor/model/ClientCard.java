package com.github.williamdlm.mscreditassessor.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ClientCard {

    private String cardName;
    private String cardBrand;
    private BigDecimal cardLimit;
}
