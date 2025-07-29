package com.renta_car.demorentacar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renta_car.demorentacar.models.Car;
import com.renta_car.demorentacar.response.ResponseHandler;
import com.renta_car.demorentacar.services.CarsService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;







@RestController
@RequestMapping("/cars")
public class CarsController {

    //Inyectamos el servicio

    private final CarsService carService;

    public CarsController(CarsService carService){
        this.carService = carService;
    }

    @GetMapping("/all")
    public ResponseEntity<Object>getAllCars(){
       return  ResponseHandler.responseBuilder("Lista de coches en la base de datos", 
        HttpStatus.OK, carService.getCarsAll());
    }
    @GetMapping("/by_id/{id}")
    public ResponseEntity<Object> getCarbyID(@PathVariable ("id") Integer id){
         return ResponseHandler.responseBuilder("Coche buscado por la "+ id, HttpStatus.OK, carService.getCarbyid(id));
    }
    @PostMapping("/insert")
    public Car insertCar(@RequestBody Car car){
        return carService.insertarCar(car);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteCar(@PathVariable ("id") Integer id){
        //Acivamos el delete del coche por id
        carService.deleteCarbyid(id);
        String message ="Se ha borrado correctamente el coche";
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<String> putCarByid(@PathVariable Integer id, @RequestBody Car car) {
        carService.updateCarbyID(id, car);
        
         String message ="Se ha borrado correctamente el coche";
        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(message);
    }

    



    


}
