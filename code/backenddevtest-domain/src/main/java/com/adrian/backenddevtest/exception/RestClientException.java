package com.adrian.backenddevtest.exception;

import lombok.NonNull;

import java.io.Serial;

public class RestClientException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2049864171052059860L;

    public RestClientException(@NonNull final String errorMessage) {
super(errorMessage);    }

}