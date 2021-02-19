package com.naar.service;

import com.naar.data.UserDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naar.model.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;

	//creates a user
	public User createUser(String name) {
		User new_user = new User();
		new_user.setName(name);
		new_user = userDao.addUser(new_user);
		return new_user;
	}

	//updates a user(name) by a user id
	public boolean updateUserById(String name, int userid){
		User user = userDao.getUserById(userid);
		user.setName(name);
		return userDao.updateUser(user);
	}

	//deletes a user by an id
	public boolean deleteUserById(int userid){
		return userDao.deleteUserbyId(userid);
	}

	//finds a user by an id
	public User findUser(int userid) {
		return userDao.getUserById(userid);
	}

	//finds a user by username
	public User findUserByName(String name) {
		List<User> users = userDao.getAllUsers();
		
		for(User user: users) {
			if(user.getName().equals(name)) {
				return user;
			}
		}
		
		return null;
	}
}
