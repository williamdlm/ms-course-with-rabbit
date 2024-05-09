package com.github.williamdlm.mscreditassessor.model;

import lombok.Data;

import java.util.List;

@Data
public class ClientStatus {
    private ClientData clientData;
    private List<ClientCard> cards;

}
