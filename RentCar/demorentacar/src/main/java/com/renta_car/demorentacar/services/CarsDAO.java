package com.renta_car.demorentacar.services;

import java.util.List;
import java.util.Optional;

import com.renta_car.demorentacar.models.Car;

public interface CarsDAO {
    List<Car> selectCars();
    int insertCar(Car car);
    Optional<Car> selectCarByid(Integer id);
    int deleteClient(Integer id);
    Optional<Car> selectCarbyPlaca(String placa);
    int updateCar(Integer id, Car car);
}
