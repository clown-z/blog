package com.blog.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_tag")
public class Tag {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	
	//多对多
	@ManyToMany(mappedBy = "tags")
	private List<Blog> blog = new ArrayList<Blog>();
	
	public Tag() {
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlog() {
		return blog;
	}


	public void setBlog(List<Blog> blog) {
		this.blog = blog;
	}


	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + "]";
	}
	
	
}
