package com.renta_car.demorentacar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta_car.demorentacar.models.Reservation;
import com.renta_car.demorentacar.services.ReservationService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/reservations")
public class ReservationController {


    //Inyectamos el servicio

    private final ReservationService reserveService;

    public ReservationController(ReservationService reserveService){
        this.reserveService = reserveService;
    }

    @GetMapping("/all")

    public List<Reservation> getAllReservations(){
        return reserveService.getReservationAll();
    }
    @GetMapping("by_id/{id}")
    public Reservation getReservationByid( @PathVariable ("id") Integer id){
        return reserveService.getReservatiobyId(id);
    }
    @GetMapping("/all/{startdate}/{endate}")

    public List<Reservation> getReservationByDate( @PathVariable ("startdate")LocalDate startDate ,
    @PathVariable ("endate")LocalDate endate ){
        //Importante--Si usas string te falla el SQL porque tu campo es Date en la base de datos. Necesitas un campo LOCALDATE PARA PODER REALIZAR LAS BUSQUEDADS
        return reserveService.getAllReservationBydates(startDate, endate);
    }

    @GetMapping("/insert")
    public ResponseEntity<Reservation>insertReservatio(@RequestBody Reservation reserva){
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(reserva);
    }

    

  
    
    




}
