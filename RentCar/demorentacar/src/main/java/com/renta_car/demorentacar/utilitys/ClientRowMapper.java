package com.renta_car.demorentacar.utilitys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.renta_car.demorentacar.models.Client;

public class ClientRowMapper implements RowMapper<Client>{

    @Override
    public Client mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return new Client(
            resultSet.getInt("id_cliente"),
            resultSet.getString("nombre"),
            resultSet.getString("direccion"),
            resultSet.getString("email"),
            resultSet.getString("telefono"),
            resultSet.getString("nickname"),
            resultSet.getString("password")
        );
    }

    //aqui ira las filas convertidas a objetos java de la base de datos.




}
