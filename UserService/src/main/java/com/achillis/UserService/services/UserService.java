package com.achillis.UserService.services;

import java.util.List;

import com.achillis.UserService.entity.User;

public interface UserService {

	User saveUser(User user);
	List<User> getAllUser();
	User getUser(String userId);
	void deleteUser(String userId);
	User updateUser(User user);
	
}
