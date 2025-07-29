package com.renta_car.demorentacar.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.renta_car.demorentacar.exceptions.NotFoundException;
import com.renta_car.demorentacar.models.Car;

@Service
public class CarsService {

    //Se hace la inyeccion de dependencias

    private final CarsDAO carDAO;

    public CarsService(CarsDAO carDAO){
        this.carDAO = carDAO;
    }

    //Funciones para los coches.
    //Mostra los coches
    //buscar coche por id
    //buscar coche por matricula
    //actualizar coche
    //eliminar coche
    //Sin DTO porque no hay datos sensibles

    public List<Car> getCarsAll(){
        return carDAO.selectCars();
    }

    public Car getCarbyid(Integer id) {
        //Recibo el cliente y uso optionals
        Car usuariobyid = carDAO.selectCarByid(id).orElseThrow(
            ()->new NotFoundException(String.format("Car with id %s not found", id)));          
     return usuariobyid;
    }

    public Car insertarCar(Car car) {
        //Verificamos si esta el coche inlcuido en la tabla.
        //Recibimos un optional y si lo encuentra es un objeto coche

        //La cosa es detectar si esta introducido y este metodo no nos vale

        if(carDAO.selectCarbyPlaca(car.getPlaca()).isPresent()){
            throw new IllegalStateException("El coche con la identificacion" + car.getPlaca() +"ya esta introducido el coche");
            //Si el coche esta metido pues salta error.
        }
        //Sino esta el coche pues se introduce
        Integer insertarCar = carDAO.insertCar(car);
        //Ponemos un verificador que se ha introducido una sola fila

        if(insertarCar !=1){
            //Lanzamos error.
            throw new IllegalStateException("Error al insertar el coche en la base de datos");
        }
        //Al no tener ningun retorno/Debemos de buscar el car introducido y devolverlo para la funcion
        //Vamos directamente a sacar el coche para devolverlo

        Car carInsertado = carDAO.selectCarbyPlaca(car.getPlaca()).orElseThrow(
            ()->new NotFoundException(String.format("No se ha recuperado el coche ")));

        //Una vez obtenemos el coche lo retornamos
        return carInsertado;
    }


    public void deleteCarbyid(Integer id){
        //Tendriamos que pasar el id y ver si esta.
        //Si esta se elimina, sino no se elimina.
        //Descartamos usar el que trae el objeto car por posibles nulos
        Optional<Car> obtenerCarbyDele = carDAO.selectCarByid(id);
        //Obtenemos el optional y lo tratamos

        obtenerCarbyDele.ifPresentOrElse(
            car ->{
                carDAO.deleteClient(id);
            },
            () ->{
                //Sino se cumple pues le salto la excepcion.
                 throw new NotFoundException(String.format("Car with id %s not found", id));

            }
        );    
    }

      public void updateCarbyID(Integer id, Car car ){
        //Al pasarle el id nos traeri el ooptional y le pasaria el if else trhow
        Optional<Car>obtenerCar = carDAO.selectCarByid(id);
        obtenerCar.ifPresentOrElse(
            carExistente ->{
                carDAO.updateCar(id, car);
            }, 
        ()->{
             // Si no existe, se lanza excepción
            throw new NotFoundException(String.format("Car with id %s not found", id));
        }
        
        );
    }    

    



}
