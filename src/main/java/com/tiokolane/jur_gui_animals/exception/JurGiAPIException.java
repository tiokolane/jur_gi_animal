package com.tiokolane.jur_gui_animals.exception;

import org.springframework.http.HttpStatus;

public class JurGiAPIException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public JurGiAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public JurGiAPIException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
