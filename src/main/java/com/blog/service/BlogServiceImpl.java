package com.blog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.BlogDao;
import com.blog.domain.Blog;
import com.blog.domain.Type;
import com.blog.handler.NotFoundException;
import com.blog.vo.BlogQuery;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public Blog getBlog(Long id) {
		return blogDao.getOne(id);
	}

	@Override
	public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
		return blogDao.findAll(new Specification<Blog>() {

			@Override
			public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(!"".equals(blog.getTitle()) && blog.getTitle() != null) {
					predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
					
				}
				if (blog.getTypeId() != null) {
					predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
				}
				if (blog.isRecommend()) {
					predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"), blog.isRecommend()));
				}
				query.where(predicates.toArray(new Predicate[predicates.size()]));
				return null;
			}
		}, pageable);
		
	}

	@Transactional
	@Override
	public Blog saveBlog(Blog blog) {
		blog.setCreateTime(new Date());
		blog.setUpdateTime(new Date());
		blog.setViews(0);
		return blogDao.save(blog);
	}

	@Transactional
	@Override
	public Blog updateBlo(Long id, Blog blog) {
		Blog b = blogDao.getOne(id);
		if( b == null) {
			throw new NotFoundException("该博客不存在");
		}
		BeanUtils.copyProperties(b, blog);
		return blogDao.save(b);
	}

	@Transactional
	@Override
	public void deleteBlog(Long id) {
		blogDao.deleteById(id);
	}

}
