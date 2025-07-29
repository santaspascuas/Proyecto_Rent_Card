package com.renta_car.demorentacar.models;

import java.io.Serializable;
import java.util.List;



public class Client  implements Serializable{
    private Integer idCliente;
    private String name;
    private String direccion;
    private String phone;
    private String email;
    private String nickname;
    private String password;

    private List<Reservation> reservations;



    //Generar los constructires

    public String getNickname() {
        return nickname;
    }


    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Client(Integer idCliente, String name, String direccion,  String email, String phone, String nickname, String password){
        this.idCliente = idCliente;
        this.name = name;
        this.direccion = direccion;
        this.phone = phone;
        this.email = email;
        this.nickname=nickname;
        this.password = password;
    }

    //Por si acaso constructor vacio.

    public Client(){

    }



    public Integer getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Client other = (Client) obj;
        if (idCliente == null) {
            if (other.idCliente != null)
                return false;
        } else if (!idCliente.equals(other.idCliente))
            return false;
        return true;
    }

    

    


}
