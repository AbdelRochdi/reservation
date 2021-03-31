package com.youcode.reservationApp.repositories;

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
import com.youcode.reservationApp.entities.ReservationLimit;
import com.youcode.reservationApp.entities.Users;

@Repository
public class ReservationRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public List<Reservation> getAllReservationsById(Long id) {
		
		Session session = sessionFactory.openSession();
		
		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("select u from Users u JOIN FETCH u.reservations r where u.id =:id order by r.date desc ", Users.class);
		
		query.setParameter("id", id);
		
		try {
			Users user = (Users) query.getSingleResult();
			reservations = user.getReservations();
		} catch (Exception e) {
			System.out.println("no results found in the database");
		}
		
		
		return reservations;
	}
	
	@Transactional
	public List<Reservation> getAllReservationsTodayByType(String type) {
		
		Session session = sessionFactory.getCurrentSession();

		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("from Reservation r where DATE(r.date) = :date and type =:type");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		query.setParameter("date", dt);
		
		query.setParameter("type", type);
		reservations = query.getResultList();
 
		return reservations;
	}
	
	@Transactional
	public ReservationLimit getTodayReservationLimit(String type) {
		
		Session session = sessionFactory.getCurrentSession();

		ReservationLimit reservationLimit = new ReservationLimit();
		
		Query query = session.createQuery("from ReservationLimit r where DATE(r.date) = :date and r.type=:type");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		query.setParameter("date", dt);
		
		query.setParameter("type", type);
		try {			
			reservationLimit = (ReservationLimit) query.getSingleResult();
		} catch (Exception e) {
		}
		
		return reservationLimit;
	}
	
	@Transactional
	public List<Reservation> getAllActiveReservationsToday(String type) {
		
		Session session = sessionFactory.getCurrentSession();

		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("from Reservation r where DATE(r.date) = CURDATE() and r.state = 'active' and r.type=:type");
		query.setParameter("type", type);

		try {
			reservations = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
 
		return reservations;
	}
	
	@Transactional
	public List<Reservation> getAllActiveReservationsTomorrow(String type) {
		
		Session session = sessionFactory.getCurrentSession();

		List reservations = new ArrayList<Reservation>();
		
		Query query = session.createQuery("from Reservation r where DATE(r.date) = :date and r.state = 'active' and r.type=:type");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		query.setParameter("date", dt);
		
		query.setParameter("type", type);

		try {
			reservations = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
 
		return reservations;
	}
	
	@Transactional
	public Reservation getReservationByTypeById(Long id, String type) {
		
		Session session = sessionFactory.getCurrentSession();

		Reservation reservation = new Reservation();
		
		Query query = session.createQuery("select r from Reservation r join fetch r.user u where DATE(r.date) = :date and r.type=:type and u.id=:id");
		
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		query.setParameter("date", dt);
		
		query.setParameter("id", id);
		query.setParameter("type", type);
		try {
			reservation = (Reservation) query.getSingleResult();
		} catch (Exception e) {
			System.out.println("no results found in the database today "+type);
			e.printStackTrace();
		}
 
		return reservation;
	}
	
	@Transactional
	public void deleteReservationByTypeById(Long id, String type) {
		
		Session session = sessionFactory.getCurrentSession();

		Reservation reservation = new Reservation();
		
		Query query = session.createQuery("select r from Reservation r join fetch r.user u where DATE(r.date) = :date and r.type=:type and u.id=:id");
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		query.setParameter("date", dt);
		
		query.setParameter("id", id);
		query.setParameter("type", type);
		
		reservation = (Reservation) query.getSingleResult();
		
		session.delete(reservation);
	
	}
	
	@Transactional 
	public void markPresence(Long id, String presence) {
		
		Session session = sessionFactory.getCurrentSession();

		Reservation reservation = session.get(Reservation.class, id);
		
		reservation.setPresence(presence);
		
		session.saveOrUpdate(reservation);
	
	}
	
	
	
	
	
	

	
	
	
}
