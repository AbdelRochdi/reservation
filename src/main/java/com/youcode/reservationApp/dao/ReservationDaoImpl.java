package com.youcode.reservationApp.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.youcode.reservationApp.entities.Reservation;
import com.youcode.reservationApp.entities.Users;

@Repository
public class ReservationDaoImpl implements ReservationDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	@Transactional
	public Reservation getById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Reservation reservation = session.get(Reservation.class, id);
		
		return reservation;
	}

	@Override
	@Transactional
	public void addReservation(Long userId, String type) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Reservation newReservation = new Reservation();
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		
		Date tomorrow = calendar.getTime();
		
		newReservation.setType(type);
		newReservation.setDate(tomorrow);
		newReservation.setPresence("none");
		
		Users users = session.get(Users.class, userId);
		
		users.addReservation(newReservation);
		
		session.saveOrUpdate(users);

	}

	@Override
	@Transactional
	public void deleteReservation(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Reservation reservation = getById(id);
		
		session.delete(reservation);
		
	}

	@Override
	@Transactional
	public void updateReservation(Reservation updatedReservation) {
		
		Session session = sessionFactory.getCurrentSession();

		session.update(updatedReservation);
		
	}

	@Override
	@Transactional
	public List<Reservation> getAllReservations() {
		
		Session session = sessionFactory.getCurrentSession();

		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("from Reservation");
		
		reservations = query.getResultList();
 
		return reservations;
	}
	
}
