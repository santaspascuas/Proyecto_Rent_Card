package com.renta_car.demorentacar.utilitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.renta_car.demorentacar.models.Car;

public class CarRowMapper  implements RowMapper<Car>{

    @Override
    public Car mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Car(
            resultSet.getInt("id_car"),
            resultSet.getString("placa"),
            resultSet.getString("modelo"),
            resultSet.getString("marca"),
            resultSet.getString("color")
            );
    }
    //Aqui convierte las filas en objetos de java que extraemos de la base de datos.
    

}
