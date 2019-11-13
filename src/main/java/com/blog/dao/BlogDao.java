package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.blog.domain.Blog;

public interface BlogDao extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog>{
	
}
