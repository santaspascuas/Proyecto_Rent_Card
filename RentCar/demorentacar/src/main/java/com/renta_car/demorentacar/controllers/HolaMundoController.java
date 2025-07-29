package com.renta_car.demorentacar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.renta_car.demorentacar.services.WelcomeService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/saludar")
public class HolaMundoController {

    private final WelcomeService servicesaludo;

    //contrsuctor

    public HolaMundoController(WelcomeService servicesaludo){
        this.servicesaludo = servicesaludo;

    }


    //Getmapping
    @GetMapping("/hola")
    public String bienvenida(){
        return servicesaludo.saludoBienvenida();
    }
    


}
