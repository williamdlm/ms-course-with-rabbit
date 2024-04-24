package com.github.williamdlm.mscard.model;

import com.github.williamdlm.mscard.enums.CardBrand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cardNumber;
    private Double income;
    private Double cardLimit;
    @Enumerated(EnumType.STRING)
    private CardBrand cardBrand;

    public Card(String name, String cardNumber, Double income, Double cardLimit, CardBrand cardBrand){
        this.name = name;
        this.cardNumber = cardNumber;
        this.income = income;
        this.cardLimit = cardLimit;
        this.cardBrand = cardBrand;
    }
}
