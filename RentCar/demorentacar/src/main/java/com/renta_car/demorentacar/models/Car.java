package com.renta_car.demorentacar.models;

import java.io.Serializable;
import java.util.List;

public class Car implements Serializable {
    private Integer idCar;
    private String placa;
    private String modelo;
    private String marca;
    private String color;

    private List<Reservation>reservations;


    //Probamos a realizar la inicialización de la entidad sin la lista. Para cuando creas un coche no tienes porque crear la reserva. La reserva se crea despues. 


    public Car(Integer idCar, String placa, String modelo, String marca, String color){
        this.idCar = idCar;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
    }



    public Integer getIdCar() {
        return idCar;
    }
    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }
    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public List<Reservation> getReservations() {
        return reservations;
    }
    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    




}