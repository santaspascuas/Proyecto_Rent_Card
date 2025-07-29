package com.renta_car.demorentacar.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class demorentacarExcepctionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<demorentacarException> handleNotFound(NotFoundException ex){
        demorentacarException response = new demorentacarException(
            ex.getMessage(), HttpStatus.NOT_FOUND,LocalDateTime.now());
             return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<demorentacarException>handleBadRequest(BadRequestException be){
        demorentacarException response = new demorentacarException(
            be.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    // Aqui en este controlador puedo ir añadiendo mas excepctiones.










}
