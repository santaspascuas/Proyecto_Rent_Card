package com.renta_car.demorentacar.respositories;

public class ClientDTO {

    private Integer idCliente;
    private String name;
    private String direccion;
    private String phone;
    private String email;


    public ClientDTO(Integer idCliente, String name, String direccion, String phone, String email) {
        this.idCliente = idCliente;
        this.name = name;
        this.direccion = direccion;
        this.phone = phone;
        this.email = email;
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


    



}
