package com.github.williamdlm.mscard.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class ClientCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    @ManyToOne
    @JoinColumn
    private Card card;
    private BigDecimal income;

    public ClientCard(String document, Card card, BigDecimal income){
        this.document = document;
        this.card = card;
        this.income = income;
    }
}
