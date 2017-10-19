package com.ssm.manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssm.commons.JacksonUtils;
import com.ssm.manager.dao.RedisDao;
import com.ssm.manager.mapper.UserMapper;
import com.ssm.manager.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisDao redisDao;
	@Override
	public List<User> getUsers() {
		return userMapper.getUsers();
	}

	@Override
	public void insertUser(User user) {
		
		userMapper.insertUser(user);
		String userJson = redisDao.get("user_" + user.getId());
		if(StringUtils.isEmpty(userJson)){
			redisDao.set("user_" + user.getId(), JacksonUtils.objectToJson(user));
		}
		
	}

	@Override
	public User getUserById(String id) {
		String userJson = redisDao.get("user_" + id);
		User user = null;
		if(StringUtils.isEmpty(userJson)){
			user = userMapper.getUserById(id);
			//不存在,设置
			if(user != null)
				redisDao.set("user_" + id, JacksonUtils.objectToJson(user));
		}else{
			user = JacksonUtils.jsonToPojo(userJson, User.class);
		}
		return user;
	}

}
