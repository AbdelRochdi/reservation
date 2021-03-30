package com.youcode.reservationApp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.youcode.reservationApp.entities.Users;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public Users getById(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Users user = session.get(Users.class, id);
		
		return user;
	}

	@Override
	@Transactional
	public Long addUser(Users user) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(user);
		
		Long id = user.getId();
	
		return id;
	}

	@Override
	@Transactional
	public void deleteUser(Long id) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Users user = getById(id);
		
		session.delete(user);
		
	}

	@Override
	@Transactional
	public void updateUser(Users updatedUser) {
		
		Session session = sessionFactory.getCurrentSession();

		Users user = getById(updatedUser.getId());

		session.update(updatedUser);
		
	}

	@Override
	@Transactional
	public List<Users> getAllUsers() {
		
		Session session = sessionFactory.getCurrentSession();

		List users = new ArrayList<Users>();
		
		Query query = session.createQuery("from Users");
		
		users = query.getResultList();
 
		return users;
	}
	
}
