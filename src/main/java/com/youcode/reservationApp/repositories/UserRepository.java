package com.youcode.reservationApp.repositories;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
}
