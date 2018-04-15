package com.erp.dashboard.dao;

import java.util.List;

import com.erp.dashboard.model.User;

public interface IUserDao {
	public List<User> getAllUsers();
	public User checkUserLogin(User user);
}
