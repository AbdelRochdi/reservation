package com.youcode.reservationApp.dao;

import java.util.ArrayList;
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
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Reservation getById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Reservation reservation = session.get(Reservation.class, id);
		
		return reservation;
	}

	@Override
	@Transactional
	public void addReservation() {
		
		Session session = sessionFactory.getCurrentSession();
		
		Reservation newReservation = new Reservation();
		
		Users users = session.get(Users.class, 5L);
		
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

		Reservation reservation = getById(updatedReservation.getReservationId());

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
