package com.blog.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.domain.Tag;

public interface TagDao extends JpaRepository<Tag, Long>{
	Tag findByName(String name);
}
