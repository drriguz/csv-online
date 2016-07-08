package com.riguz.csvonline.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.riguz.csvonline.model.User;

public interface UserService {
	public List<User> getUsers(String name, String email);

	public List<User> getUsers(String name, String email, RowBounds page);

	public User getUser(String id);

	public boolean updateUser(User user);

	public boolean deleteUser(String id);

	public boolean addUser(User user);
}
