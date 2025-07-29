package com.renta_car.demorentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.renta_car.demorentacar.exceptions.NotFoundException;
import com.renta_car.demorentacar.models.Client;
import com.renta_car.demorentacar.respositories.ClientDTO;

@Service
public class ClientService {
    private final ClientDAO clientDao;


    //Inyectamos las dependencias.
    public ClientService(ClientDAO clientDao){
        this.clientDao = clientDao;
    }

    //Mostramos los clientes

    public List<ClientDTO> getClients(){

        List<Client> clientsList =clientDao.selectClientes(); // extraes el cliente total

        //Usamos el dto para sacar lo que necesitamos.

                    List<ClientDTO> dtos = clientsList.stream()
                .map(client -> new ClientDTO(
                    client.getIdCliente(),
                    client.getName(),
                    client.getDireccion(),
                    client.getPhone(),
                    client.getEmail()))
                .toList();
        return dtos;
    }

    //Obtener cliente por el id.

    public ClientDTO getClientbyid(Integer id){

        //Como ahora manejamos DTOS-> lO LOGICO ES QUE NOS DEVULEVA UN DTO
        //No podemos usar ifpresentorelse porque son lambdas que devuelve void
        //procederemos a usar el que devuelve el objeto y tira error

        Client clientedevuelto = clientDao.selectClienteByid(id).orElseThrow(
            ()->new NotFoundException(String.format("Cliente with id %s not found", id)));

       return new ClientDTO(
        clientedevuelto.getIdCliente(),
        clientedevuelto.getName(),
        clientedevuelto.getDireccion(),
        clientedevuelto.getPhone(),
        clientedevuelto.getEmail());
    }


    public ClientDTO insertarClient(Client cliente){

        //Primetro comprobar si el id pero al ser autonmatico no lo meto
        //Compruebo con el gmail
        //Luego insertar la pelicula

        if(clientDao.selectClienteByEmail(cliente.getEmail()).isPresent()){
            throw new IllegalStateException("El usuario con nombre " + cliente.getName() + " ya tiene una cuenta asociada.");
        }

        // Sino existe pues ya empezamos a crear// 

        int insertarCliente = clientDao.insertCliente(cliente);

        //Verificamos que la inserción del Cliente en la base de datos ha sido correctaº
        if(insertarCliente!=1){
             throw new IllegalStateException("Error al insertar el cliente en la base de datos.");
        }

        //Una vez insertado, deberiamos recuperar el cliente para luego mostrar el DTOº

        // Recuperamos el cliente insertado usando el email (único), ya que es posible que despues de la inserción el id no se haya generado por la base de datos

        Client clienteInsertado = clientDao.selectClienteByEmail(cliente.getEmail())
        .orElseThrow(
            ()->new IllegalStateException("No se pudo recuperar el cliente después de la inserción.")
        );

        //Recupero al cliente y luego uso la funcion de conseguir la información y sacar el DTO

        return getClientbyid(clienteInsertado.getIdCliente());

    }

    public ClientDTO updateCliente(Client cliente){

        //Le pasamos el body para actualizarlo.->Seria buscar el id. Fallamos porque si traeigo el cliente no puedo usar las lambdas de optional

        Optional<Client> obteneroptionalclien = clientDao.selectClienteByEmail(cliente.getEmail());

        //Ahora podemos usar las lambdas pero no podemos retornar nada y nos da fallos
        //Vemos que podemos hacer maps y con elsethrow. El map lo que hace con los ptionals es buscar el valor
        //Actualizamos el valor y devolvemos el valor para tener el cliente actualizado

        Client clienteupdate = obteneroptionalclien.map(
            clienteexis ->{
                clienteexis.setName(cliente.getName());
                clienteexis.setDireccion(cliente.getDireccion());
                clienteexis.setEmail(cliente.getEmail());
                clienteexis.setPhone(cliente.getPhone());
                clienteexis.setNickname(cliente.getNickname());
                clienteexis.setPassword(cliente.getPassword());

                 // Actualizar en la base de datos
            Integer actualizado = clientDao.updateCliente(clienteexis);

            if(actualizado != 1){
                throw new IllegalStateException("Error al actualizar datos del cliente");
            }
            //El map retorna el valor del cliente actualizado.
            return clienteexis;
            }).orElseThrow(
            ()->new IllegalStateException("No se pudo actualizar al cliente la información")
        );

        //Ahora seria retornar los dto

        return getClientbyid(clienteupdate.getIdCliente());
    }



    public void eliminarClient(Integer id){

        //Primero busca con el id.Traigo el optional y si esta genial y sino pues error

        Client cliente = clientDao.selectClienteByid(id).orElseThrow(
            () ->new IllegalStateException("No existe ese cliente")
        );

        //Ahora tenemos el cliente y lo vamos a eliminar

        Integer deletecliente = clientDao.deleteClient(cliente.getIdCliente());

        //Devuelve 1 si se elimino y 0 si hubo algun problema

           // Validar que se haya eliminado
    if (deletecliente != 1) {
        throw new IllegalStateException("No se pudo eliminar el cliente con id: " + id);
    }

    }















    


}
