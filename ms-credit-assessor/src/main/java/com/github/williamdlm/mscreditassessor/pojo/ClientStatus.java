package com.github.williamdlm.mscreditassessor.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientStatus {
    private ClientData clientData;
    private List<ClientCard> cards;

}
