package com.naar.data;

import java.util.List;

import com.naar.model.User;

public interface UserDao {
	
	List<User> getAllUsers();
	User getUserById(int id);
	User addUser(User user);
	boolean updateUser(User user);
	boolean deleteUserbyId(int id);
}
