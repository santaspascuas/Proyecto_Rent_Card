package com.renta_car.demorentacar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class demorentacarException {

    private final String message;
    private final HttpStatus status;
    private final LocalDateTime timestamp;

    
    public demorentacarException(String message, HttpStatus status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    


    


}
