package com.adrian.backenddevtest.exception;

public class RestClientException extends RuntimeException {

    private static final long serialVersionUID = -2049864171052059860L;

    public RestClientException(final String clientName) {
        super(String.format("Error when calling API: %s", clientName));
    }

}