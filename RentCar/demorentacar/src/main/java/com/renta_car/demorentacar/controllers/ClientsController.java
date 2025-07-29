package com.renta_car.demorentacar.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.renta_car.demorentacar.models.Client;
import com.renta_car.demorentacar.respositories.ClientDTO;
import com.renta_car.demorentacar.services.ClientService;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;






@RestController
@RequestMapping("/clients")
public class ClientsController {

    //Inyectamos el servicio


    private final ClientService clientService;

    public ClientsController(ClientService clientService){
        this.clientService = clientService;
    }


    @GetMapping("/all")
    public List<ClientDTO> getAllClient(){
        return clientService.getClients();
    }
    @GetMapping("/by_id/{id}")
    public ClientDTO getClientbydID(@PathVariable ("id") Integer id) {
        return clientService.getClientbyid(id);
    }

    @PostMapping("/insert")
    public ResponseEntity<ClientDTO>insertarClientDTO(@RequestBody Client cliente){
        return new ResponseEntity<ClientDTO>(clientService.insertarClient(cliente),HttpStatus.CREATED);
    }
    @PutMapping("/update")

    public ResponseEntity<ClientDTO>actualizarClientDTO(@RequestBody Client cliente){
        return  ResponseEntity.status(HttpStatus.OK)
        .body(
            clientService.updateCliente(cliente));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String>deleteClientDTO(@PathVariable ("id") Integer id){
        //Aqui metemos el servicio para identificar el id
        
        String mensaje = String.format("Película  eliminada con exito");
        return ResponseEntity.status(HttpStatus.OK)
        .body(mensaje);

    }
   



}
