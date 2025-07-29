package com.renta_car.demorentacar.utilitys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.renta_car.demorentacar.models.Reservation;

//Aqui moldeamos los datos a datos java

public class ReservationRowMapper  implements RowMapper<Reservation>{

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Reservation(
            rs.getInt("id_reservation"),
            rs.getString("estado"),
            LocalDate.parse(rs.getString("star_date")),
            LocalDate.parse(rs.getString("end_date")),
            rs.getInt("cost"),
            rs.getString("grade"),
            rs.getString("comment"),
            rs.getInt("cliente_fk"),
            rs.getInt("vehiculo_fk")
        );

    //Aqui convertimos las filas en objetos de java que extraemos de la base de datos

}


}