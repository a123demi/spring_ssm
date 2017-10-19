package com.ssm.manager.mapper;


import java.util.List;

import com.ssm.manager.pojo.User;


public interface UserMapper {
	public List<User> getUsers();
	
	public void insertUser(User user);
	
	public User getUserById(String id);
}
