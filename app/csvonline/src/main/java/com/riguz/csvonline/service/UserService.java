package com.riguz.csvonline.service;

import java.util.List;

import com.riguz.csvonline.model.User;

public interface UserService {
	public List<User> getUsers(String name, String email);

	public User getUser(String id);

	public boolean updateUser(User user);

	public boolean deleteUser(String id);

	public boolean addUser(User user);
}
