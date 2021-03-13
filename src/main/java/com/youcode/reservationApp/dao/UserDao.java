package com.youcode.reservationApp.dao;

import java.util.List;

import com.youcode.reservationApp.entities.Users;

public interface UserDao {
	
	Users getById(Long id);
	List<Users> getAllUsers();
	void addUser(Users user);
	void deleteUser(Long id);
	void updateUser(Users user);

}
