package com.youcode.reservationApp.dao;

import java.util.List;

import com.youcode.reservationApp.entities.Reservation;

public interface ReservationDao {
	
	Reservation getById(Long id);
	List<Reservation> getAllReservations();
	void addReservation();
	void deleteReservation(Long id);
	void updateReservation(Reservation reservation);

}
