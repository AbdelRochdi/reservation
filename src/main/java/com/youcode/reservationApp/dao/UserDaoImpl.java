package com.youcode.reservationApp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.youcode.reservationApp.entities.Users;

public class UserDaoImpl implements UserDao {

	@Override
	public Users getById(Long id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx =  session.beginTransaction();
		Users user = session.get(Users.class, id);
		 
		try {
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		
		return user;
	}

	@Override
	public void addUser(Users user) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx =  session.beginTransaction();
		session.persist(user);
		
		try {
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
					tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		
	}

	@Override
	public void deleteUser(Long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx =  session.beginTransaction();
		Users user = getById(id);
		session.delete(user);
		
		try {
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
					tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		
	}

	@Override
	public void updateUser(Users updatedUser) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx =  session.beginTransaction();
		Users user = getById(updatedUser.getId());

		session.update(updatedUser);
		
		try {
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
					tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
		
	}

	@Override
	public List<Users> getAllUsers() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx =  session.beginTransaction();
		List users = new ArrayList<Users>();
		
		Query query = session.createQuery("from Users");
		
		users = query.getResultList();
		
		try {
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
					tx.rollback();
			}
			e.printStackTrace();
		}finally {
			if (session!=null) {
				session.close();
			}
		}
 
		return users;
	}
	
}
