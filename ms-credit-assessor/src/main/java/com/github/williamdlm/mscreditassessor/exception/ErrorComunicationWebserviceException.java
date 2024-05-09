package com.github.williamdlm.mscreditassessor.exception;

import lombok.Getter;

@Getter
public class ErrorComunicationWebserviceException extends Exception {

    private Integer status;
    public ErrorComunicationWebserviceException(String msg, Integer status){
        super(msg);
        this.status = status;
    }
}
