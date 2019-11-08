package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.domain.Type;

public interface TypeDao extends JpaRepository<Type, Long>{
	
}
