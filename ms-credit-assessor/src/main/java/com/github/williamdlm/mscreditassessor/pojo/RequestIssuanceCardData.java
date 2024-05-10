package com.github.williamdlm.mscreditassessor.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RequestIssuanceCardData {
    private Long idCard;
    private String document;
    private String address;
    private BigDecimal grantedLimit;
}
