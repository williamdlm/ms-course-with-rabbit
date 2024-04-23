package com.github.williamdlm.msclient.dto;

import com.github.williamdlm.msclient.model.Client;

public record ClientDTO(String name, String idDocument) {
    public Client toModel() {
        return new Client(name,idDocument);
    }
}
