package com.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index() {
//		int i = 9/0; 
//		String blog = null;
//		if (blog == null) {
//			throw new NotFoundException("博客不存在");
//		}
		System.out.println("--------index---------");
		return "index";
	}
	
	@RequestMapping("/blog")
	public String blog() {
		return "blog";
	}
	 
	@RequestMapping("/login")
	public String login() {
		return "admin/login";
	}
}
