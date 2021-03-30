package com.youcode.reservationApp.repositories;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.youcode.reservationApp.entities.ReservationLimit;
import com.youcode.reservationApp.entities.UserReputation;
import com.youcode.reservationApp.entities.Users;

@Repository
public class UserRepository {

	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public Users getByEmail(String email) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from Users u where u.email=:email");
		query.setParameter("email", email);
		
		try {
			List<Users> users = query.getResultList();
			if (users.size() > 0) {
				Users user = users.get(0);
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Transactional
	public void setLimit(int limit,String type,Date date) {
		
		Session session = sessionFactory.getCurrentSession();
		
		ReservationLimit reservationLimit = new ReservationLimit();
		
		Query query = session.createQuery("from ReservationLimit r where r.date=:date and r.type=:type");
		query.setParameter("date", date);
		query.setParameter("type", type);
		try {
			reservationLimit = (ReservationLimit) query.getSingleResult();
		} catch (Exception e) {
		}
		
		reservationLimit.setReservationLimit(limit);
		reservationLimit.setDate(date);
		reservationLimit.setType(type);
		
		session.saveOrUpdate(reservationLimit);
	
	}
	
	
	
	@Transactional
	public List<ReservationLimit> getAllReservationLimits() {
		
		Session session = sessionFactory.getCurrentSession();

		List reservationLimit = new ArrayList<ReservationLimit>();
		
		Query query = session.createQuery("from ReservationLimit r where DATE(r.date) > CURDATE() order by r.date desc");
		
		reservationLimit = query.getResultList();
 
		return reservationLimit;
	}
	
	@Transactional 
	public void addPresence(Long id, int points) {
		
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, id);
		UserReputation userReputation = new UserReputation();
		if (user.getUserReputation() == null) {
			userReputation.setPresence(points);
			user.setUserReputation(userReputation);
		}else {
			userReputation = user.getUserReputation();
			userReputation.setPresence(userReputation.getPresence()+points);
		}
		
		session.saveOrUpdate(user);
		
	}
	
	@Transactional 
	public void deducePresence(Long id, int points) {
		
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, id);
		UserReputation userReputation = new UserReputation();
		if (user.getUserReputation() == null) {
			userReputation.setPresence(points);
			user.setUserReputation(userReputation);
		}else {
			userReputation = user.getUserReputation();
			userReputation.setPresence(userReputation.getPresence()-points);
		}
		
		session.saveOrUpdate(user);
		
	}
	
	@Transactional 
	public void addAbsence(Long id, int points) {
		
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, id);
		UserReputation userReputation = new UserReputation();
		if (user.getUserReputation() == null) {
			userReputation.setAbsence(points);
			user.setUserReputation(userReputation);
		}else {
			userReputation = user.getUserReputation();
			userReputation.setAbsence(userReputation.getAbsence()+points);
		}
		
		session.saveOrUpdate(user);
		
	}
	
	@Transactional 
	public void deduceAbsence(Long id, int points) {
		
		Session session = sessionFactory.getCurrentSession();
		Users user = session.get(Users.class, id);
		UserReputation userReputation = new UserReputation();
		if (user.getUserReputation() == null) {
			userReputation.setAbsence(points);
			user.setUserReputation(userReputation);
		}else {
			userReputation = user.getUserReputation();
			userReputation.setAbsence(userReputation.getAbsence()-points);
		}
		
		session.saveOrUpdate(user);
		
	}
	
}
