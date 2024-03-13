package com.synthilearn.workareaservice.infra.rest.exception;

import com.synthilearn.workareaservice.infra.rest.exception.parent.GenericException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class DictionaryException extends GenericException {
    public DictionaryException(String message, HttpStatus status, Integer code) {
        super(message, status, code);
    }

    public static DictionaryException creationError(UUID workareaId, String body) {
        return new DictionaryException(String.format("Error has occurred while create dictionary for workarea: %s, bodyError: %s", workareaId, body),
                HttpStatus.INTERNAL_SERVER_ERROR, 1000);
    }
}
