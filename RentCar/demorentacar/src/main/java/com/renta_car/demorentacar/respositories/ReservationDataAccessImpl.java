package com.renta_car.demorentacar.respositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.renta_car.demorentacar.models.Reservation;
import com.renta_car.demorentacar.services.ReservationDAO;
import com.renta_car.demorentacar.utilitys.ReservationRowMapper;



@Repository
public class ReservationDataAccessImpl implements ReservationDAO {


    
    //Implementamos las inyecciones de dependencias de jdctemplate

    private final JdbcTemplate jdbcTemplate;

    //iNYECTAMOS
    public ReservationDataAccessImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;

    }


    @Override
    public List<Reservation> selectReservations() {
        String sql = "SELECT * FROM reservation";
        return jdbcTemplate.query(sql , new ReservationRowMapper());
    }

    @Override
    public Optional<Reservation> selectReservationById(Integer id) {
        String sql = "SELECT id_reservation, estado, star_date, end_date, cost, grade , comment, cliente_fk, vehiculo_fk FROM reservation WHERE id_reservation = ?";
        //Como retornamos optionals pues debemos de capturar la lista
        List<Reservation> reservas = jdbcTemplate.query(sql, new ReservationRowMapper(), id);
        //Ahora a la lista le usamos un map para sacar el varlo y retornar el optional con stream
        //No le aplicamos filter porque ya filtramos en el sql y nos dara solo un valor
        return reservas.stream().findFirst();
    }

    @Override
    public int insertReservation(Reservation reserv) {
        String sql ="""
                INSERT INTO reservation(
                estado, star_date, end_date, cost, grade , comment, cliente_fk, vehiculo_fk)
                VALUES (?,?,?,?,?,?,?,?)
                """;
        return jdbcTemplate.update(sql,
        reserv.getEstado(),
        reserv.getStartDate(),
        reserv.getEnDate(),
        reserv.getCost(),
        reserv.getComment(),
        reserv.getClienteFk(),
        reserv.getVehiculoFk());        
    }

    @Override
    public int deleteReservation(Integer id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservation'");
    }


     @Override
     public List<Reservation> selectReservationpordate(LocalDate startDate, LocalDate endDat) {
         //Importante--Si usas string te falla el SQL porque tu campo es Date en la base de datos. Necesitas un campo LOCALDATE PARA PODER REALIZAR LAS BUSQUEDADS
         String sql = "SELECT id_reservation, estado, star_date, end_date, cost, grade , comment, cliente_fk, vehiculo_fk FROM reservation WHERE star_date BETWEEN ? AND ?";
          return jdbcTemplate.query(sql, new ReservationRowMapper(), startDate, endDat  );
    }


     @Override
     public Optional<Reservation> searchreservationwithcondition(LocalDate startDate, LocalDate endDat, Integer id_cli,
            Integer id_car) {
         String sql = "SELECT  id_reservation, estado, star_date, end_date, cost, grade , comment, cliente_fk, vehiculo_fk FROM reservation WHERE cliente_fk = ? AND vehiculo_fk = ? AND star_date = ? AND end_date = ?";
         //Como es unptiona, usaremos una lista y usaremos los filtros
         List<Reservation> reserva = jdbcTemplate.query(sql, new ReservationRowMapper(), startDate, endDat, id_cli,id_car);
         //Ahora a la lista le usamos un map para sacar el varlo y retornar el optional con stream
        //No le aplicamos filter porque ya filtramos en el sql y nos dara solo un valor
        return reserva.stream().findFirst();

     }
       
   


  

}
