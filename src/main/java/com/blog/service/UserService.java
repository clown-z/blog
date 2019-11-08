package com.blog.service;

import com.blog.domain.User;

public interface UserService {
	
	User checkUser(String username, String password);
}
