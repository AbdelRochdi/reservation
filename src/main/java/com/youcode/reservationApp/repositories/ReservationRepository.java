package com.youcode.reservationApp.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.youcode.reservationApp.entities.Reservation;
import com.youcode.reservationApp.entities.Users;

@Repository
public class ReservationRepository {

	@Autowired
	private SessionFactory sessionFactory;
	

	public List<Reservation> getAllReservationsById(Long id) {
		
		Session session = sessionFactory.openSession();
		
		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("select u from Users u JOIN FETCH u.reservations where u.id =:id", Users.class);
		
		query.setParameter("id", id);
		
		try {
			Users user = (Users) query.getSingleResult();
			reservations = user.getReservations();
		} catch (Exception e) {
			System.out.println("no results found in the database");
		}
		
		
		return reservations;
	}
	
}
