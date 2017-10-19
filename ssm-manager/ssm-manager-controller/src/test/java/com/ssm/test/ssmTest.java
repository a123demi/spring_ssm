package com.ssm.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.manager.pojo.User;
import com.ssm.manager.service.UserService;

public class ssmTest {
	
	private ApplicationContext ctx = null;
	private UserService userService = null;
	
	@Before
	public void init()
	{
		ctx = new ClassPathXmlApplicationContext("spring/applicationContext-service.xml");
		userService = ctx.getBean(UserService.class);
	}
	
	@Test	
	public void testGetUsers(){
		List<User> users = userService.getUsers();
		System.out.println(users);
	}
	
	@Test
	public void testInsertUser(){
		User user = new User();
		user.setPassword("123");
		user.setUserName("ssm1111");
		
		userService.insertUser(user);
	}
	
	@Test
	public void testGetUserById(){
		String id="4";
		System.out.println(userService.getUserById(id));
		
	}
	
	

}
