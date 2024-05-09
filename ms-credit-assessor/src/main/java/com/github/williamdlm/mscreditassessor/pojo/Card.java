package com.github.williamdlm.mscreditassessor.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {

    private Long id;
    private String name;
    private Double cardLimit;
    private String cardBrand;

}
