package com.renta_car.demorentacar.services;

import java.util.List;
import java.util.Optional;

import com.renta_car.demorentacar.models.Client;

public interface ClientDAO {
    List<Client> selectClientes();
    int insertCliente(Client cliente);
    Optional<Client> selectClienteByid(int id);
    int deleteClient(Integer id);
    Optional<Client> selectClienteByEmail(String email);
    int updateCliente(Client cliente);
}
