package com.github.williamdlm.mscard.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestIssuanceCardData {
    private Long idCard;
    private String document;
    private String address;
    private BigDecimal grantedLimit;
}
