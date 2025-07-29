package com.renta_car.demorentacar.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Reservation implements Serializable{

    private Integer idReservation;
    private String estado;
    private LocalDate startDate;
    private LocalDate enDate;
    private Integer cost;
    private String grade;
    private String comment;

    //Al hacer jdbcTemplate puro debo de obtener los ids del cliente y del vehiculo.
    
    private int clienteFk;
    private int vehiculoFk;



    public Reservation(Integer idReservation, String estado, LocalDate startDate, LocalDate enDate, Integer cost,String grade, String comment, int clienteFk, int vehiculoFk ){
        this.idReservation = idReservation;
        this.estado = estado;
        this.startDate = startDate;
        this.enDate = enDate;
        this.cost = cost;
        this.grade = grade;
        this.comment = comment;
        this.clienteFk = clienteFk;
        this.vehiculoFk = vehiculoFk;
    }
    //Constructor generico.

    public Reservation(){
        // El constrcutor generico.
    }



    public Integer getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(Integer idReservation) {
        this.idReservation = idReservation;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEnDate() {
        return enDate;
    }

    public void setEnDate(LocalDate enDate) {
        this.enDate = enDate;
    }


    
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public int getClienteFk() {
        return clienteFk;
    }
    public void setClienteFk(int clienteFk) {
        this.clienteFk = clienteFk;
    }
    public int getVehiculoFk() {
        return vehiculoFk;
    }
    public void setVehiculoFk(int vehiculoFk) {
        this.vehiculoFk = vehiculoFk;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }


    



}
