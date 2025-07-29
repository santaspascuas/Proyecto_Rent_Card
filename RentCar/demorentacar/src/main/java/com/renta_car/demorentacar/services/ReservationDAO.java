package com.renta_car.demorentacar.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.renta_car.demorentacar.models.Reservation;

public interface ReservationDAO {
    List<Reservation>selectReservations();
    Optional<Reservation>selectReservationById(Integer id);
    int insertReservation(Reservation reserv);
    int deleteReservation(Integer id);
    List<Reservation> selectReservationpordate(LocalDate startDate, LocalDate endDat);
    Optional<Reservation>searchreservationwithcondition(LocalDate startDate, LocalDate endDat,Integer id_cli, Integer id_car);
}
