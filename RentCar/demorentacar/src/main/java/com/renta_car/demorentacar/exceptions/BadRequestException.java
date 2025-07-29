package com.renta_car.demorentacar.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value =HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    //Ahora la clase manejador

    public BadRequestException(String message){
        super(message);
    }

}
