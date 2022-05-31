package com.rumesh.horseharnes.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
