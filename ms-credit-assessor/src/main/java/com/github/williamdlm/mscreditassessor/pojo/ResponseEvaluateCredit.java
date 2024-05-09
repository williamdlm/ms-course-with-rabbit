package com.github.williamdlm.mscreditassessor.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseEvaluateCredit {
    private List<ClientCard> grantedCards;

}
