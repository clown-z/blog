package com.blog.service;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.TypeDao;
import com.blog.domain.Type;
import com.blog.handler.NotFoundException;

@Service
public class TypeServiceImpl implements TypeService {

	@Resource
	private TypeDao typeDao;
	
	@Transactional
	@Override
	public Type saveType(Type type) {
		return typeDao.save(type);
	}

	@Transactional
	@Override
	public Type getType(Long id) {
		return typeDao.getOne(id);
	}
	
	@Override
	public Type getTypeByName(String name) {
		return typeDao.findByName(name);
	}

	@Transactional
	@Override
	public Page<Type> listType(Pageable pageable) {
		return typeDao.findAll(pageable);
	}

	@Transactional
	@Override
	public Type updateType(Long id, Type type) {
		Type t = typeDao.getOne(id);
		if(t == null) {
			throw new NotFoundException("不存在该类型");
		}
		BeanUtils.copyProperties(type, t);
		return typeDao.save(t);
	}

	@Transactional
	@Override
	public void deleteType(Long id) {		
		typeDao.deleteById(id);
	}

}
