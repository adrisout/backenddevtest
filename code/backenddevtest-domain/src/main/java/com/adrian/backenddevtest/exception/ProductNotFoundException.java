package com.adrian.backenddevtest.exception;

public class ProductNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -7638511639793332377L;

    public ProductNotFoundException(final String clientName) {
        super(String.format("Product not found: %s", clientName));
    }

}