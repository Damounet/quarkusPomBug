package com.toto.tdng.datachecker.common.model.exception;

import com.fasterxml.jackson.core.JsonProcessingException;

public class BadJsonException extends JsonProcessingException {

    public BadJsonException(String message) {
        super(message);
    }
}
