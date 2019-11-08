package com.blog.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.blog.dao.UserDao;
import com.blog.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public User checkUser(String username, String password) {
		User user = userDao.findByUsernameAndPassword(username, password);
		return user;
	}

}
