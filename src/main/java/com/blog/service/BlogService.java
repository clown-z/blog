package com.blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.blog.domain.Blog;
import com.blog.vo.BlogQuery;

public interface BlogService {
	
	Blog getBlog(Long id);
	
	Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
	
	Blog saveBlog(Blog blog);
		
	Blog updateBlo(Long id, Blog blog);
	
	void deleteBlog(Long id);
	
}
