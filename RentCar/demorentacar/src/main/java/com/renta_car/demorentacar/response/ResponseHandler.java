package com.renta_car.demorentacar.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//Esto es una clase para crear una respuesta Generica.
// Lo que hacemos es crear un funcion con response entity que le pasaremos el mensaje, el estatus de la respuesta y el Objet.
//Utilizamos object como parametro generico porque recibiremos: Listas, oobjetos y demas.

public class ResponseHandler {

    public static ResponseEntity<Object> responseBuilder(
        String message, HttpStatus httpstatus, Object responseobject
    ){
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("httpStatus", httpstatus);
        response.put("data", responseobject);


        return new ResponseEntity<>(response, httpstatus);

    }

}
