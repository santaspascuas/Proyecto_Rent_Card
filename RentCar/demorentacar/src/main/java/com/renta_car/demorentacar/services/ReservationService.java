package com.renta_car.demorentacar.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renta_car.demorentacar.exceptions.NotFoundException;
import com.renta_car.demorentacar.models.Reservation;

@Service
public class ReservationService {

    //Inyectamos las dependencias

    private final ReservationDAO reserDAO;

    public ReservationService(ReservationDAO reserDAO){
        this.reserDAO = reserDAO;
    }

    //Obtener las listas
    //Buscar por id
    //eliminar la reserva
    //actualizar 


    public List<Reservation> getReservationAll(){
        return reserDAO.selectReservations();
    }

    //Buscar el id de la reserva y retornar la reserva
    public Reservation getReservatiobyId(Integer id){
        //Retornare una reserva pero la funcion esta puesto como un optional
        Reservation verificarReserva = reserDAO.selectReservationById(id).orElseThrow(
            ()->new NotFoundException(String.format("Car with id %s not found", id)));
        return verificarReserva;    
    }

    
    //Buscar una lista entre las fechas estrictas.

    public List<Reservation> getAllReservationBydates(LocalDate startDate, LocalDate endDate){
        //Importante--Si usas string te falla el SQL porque tu campo es Date en la base de datos. Necesitas un campo LOCALDATE PARA PODER REALIZAR LAS BUSQUEDADS
        return reserDAO.selectReservationpordate(startDate, endDate);
    }

    //INSERTAR UNA RESERVA  EN LA BASE DE DATOS

    public Reservation getReservationInsertado(Reservation reserva){
        // Lo primero como siempre es poder buscar si la reserva esta ya creada
        //Hay que ver con el id pero no se lo damos, asique lo mejor es poder buscar por otro campo
        //Como el id no se lo damos, usarmos la funcion para buscarla de otra manera

        Optional<Reservation>reservaEncontrada = getInfoReservation(
            reserva.getStartDate(),
            reserva.getEnDate(),
            reserva.getClienteFk(),
            reserva.getVehiculoFk()
        );
        //Podemos usar las funciones del optional
        if(reservaEncontrada.isPresent()){
            throw new IllegalStateException("Error porque ya existe la reserva");
        }
        //Sino existe pues la inserto
        Integer insertado = reserDAO.insertReservation(reserva);

        if(insertado!=1){
            //Lanzamos error.
            throw new IllegalStateException("Error al insertar el la reserva en la base de datos");
        }

        //Ahora debemos comprobar que se ha insertado.Para eso buscamos el optional
        //Confirmamos que al crear no tengo el id porque lo genera la base de datos.

        return getInfoReservation(
            reserva.getStartDate(),
            reserva.getEnDate(),
            reserva.getClienteFk(),
            reserva.getVehiculoFk()
        ).orElseThrow(() -> new NotFoundException("No se ha recuperado la reserva"));
        //Retorna una reserva si la encuentra y sino salta la excepcion.
    }

    //Vamos a crear una función que nos verifique la reserva es unica en la fecha y por el usuario y el coche

    public Optional<Reservation>getInfoReservation(LocalDate startDate, LocalDate endDat, Integer id_cli,Integer id_car){
         return reserDAO.searchreservationwithcondition(startDate,endDat,id_cli,id_car);
         //Retornamos el optional que encuentre cuando se cumpla las conficiones.
         //Proponemos esto porque es una funcion auxiliar y no queremos que salgan excepciones
    }


    

    


































}
