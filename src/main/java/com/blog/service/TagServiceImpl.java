package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.dao.TagDao;
import com.blog.domain.Tag;
import com.blog.handler.NotFoundException;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagDao tagDao;
	
	@Override
	public Tag saveTag(Tag Tag) {
		return tagDao.save(Tag);
	}

	@Override
	public Tag getTag(Long id) {
		return tagDao.getOne(id);
	}

	@Override
	public Tag getTagByName(String name) {
		return tagDao.findByName(name);
	}

	@Override
	public Page<Tag> listTag(Pageable pageable) {
		return tagDao.findAll(pageable);
	}

	@Override
	public Tag updateTag(Long id, Tag tag) {
		Tag t = tagDao.getOne(id);
		if(t == null) {
			throw new NotFoundException("不存在该标签");
		}
		BeanUtils.copyProperties(tag, t);
		return tagDao.save(t);
	}

	@Override
	public void deleteTag(Long id) {
		tagDao.deleteById(id);
	}

	@Override
	public List<Tag> listTag() {
		return tagDao.findAll();
	}

	@Override
	public List<Tag> listTag(String ids) {//1,2,3
		return tagDao.findAll();
		//ById(convertToList(ids));
	}
	
	private  List<Long> convertToList(String ids) {
		List<Long> list = new ArrayList<Long>();
		if ("".equals(ids) && ids != null) {
			String[] idarry = ids.split(",");
			for (int i = 0; i < idarry.length; i++) {
				list.add(new Long(idarry[i]));
				
			}
		}
		return list;
	}

}
