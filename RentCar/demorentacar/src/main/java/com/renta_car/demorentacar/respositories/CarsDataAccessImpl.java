package com.renta_car.demorentacar.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renta_car.demorentacar.models.Car;
import com.renta_car.demorentacar.services.CarsDAO;
import com.renta_car.demorentacar.utilitys.CarRowMapper;


@Repository
public class CarsDataAccessImpl implements CarsDAO {

    //Inyeccion de dependencia de djdbc
    private final JdbcTemplate jdbcTemplate;


    //Contrsuctor

    public CarsDataAccessImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Car> selectCars() {
        String sql = "SELECT * FROM car";
        return jdbcTemplate.query(sql, new CarRowMapper());
    }

    @Override
    public int insertCar(Car car) {
        //Metodo de inserción del coche en la base de datos
        String sql = """
                INSERT INTO car(placa, modelo,marca,color)
                VALUES (?,?,?,?)
                """;

        return jdbcTemplate.update(sql,
        car.getPlaca(),
        car.getModelo(),
        car.getMarca(),
        car.getColor()
        );        
    }

    @Override
    public Optional<Car> selectCarByid(Integer id) {
        String sql = "SELECT id_car, placa, modelo, marca,color FROM car WHERE id_car = ?";
        //La idea esta que te da las filas enteras de todo, pero la cosa es que aqui lo trates
        List<Car> cars =  jdbcTemplate.query(sql, new CarRowMapper(),id);
        //Lo convertimos en lista y le aplicamos el poder recorrerla y encontrar la primera.º
        return cars.stream().findFirst();
    }

    @Override
    public int deleteClient(Integer id) {

        String sql = """
                DELETE FROM car WHERE id_car = ?
                """;
        return jdbcTemplate.update(sql, id);            
    }

    @Override
    public Optional<Car> selectCarbyPlaca(String placa) {
        String sql = "SELECT id_car, placa, modelo, marca,color FROM car WHERE placa = ?";
        //Altener una lista pues rcibo en lista y debo de darle unptional
        List<Car> carsObtenidos = jdbcTemplate.query(sql, new CarRowMapper(), placa);
        //Ahora debemos de darle la opcion
        return carsObtenidos.stream().findFirst();
        //Al ser optionals debemos de trabajar asi con listas.No hace falta el filtro porque ya le estas filtrando en el sql
    }

    @Override
    public int updateCar(Integer id, Car car) {
        String sql ="UPDATE car SET placa = ?, modelo = ?, marca = ?, color = ?";
        return jdbcTemplate.update(sql,
        car.getPlaca(),
        car.getModelo(),
        car.getMarca(),
        car.getColor());
    }

}




