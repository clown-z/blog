package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.domain.User;

public interface UserDao extends JpaRepository<User, Long>{
	 
	User findByUsernameAndPassword(String username, String password);
	
}
