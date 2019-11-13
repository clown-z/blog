package com.blog.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.domain.Type;
import com.blog.service.BlogService;
import com.blog.service.TypeService;
import com.blog.vo.BlogQuery;

@Controller
@RequestMapping("/admin")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@GetMapping("/blogs")
	public String blog(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable, 
			BlogQuery blog, Model model) {
		
		model.addAttribute("types", typeService.listType());
		for (Type t : typeService.listType()) {
			System.out.println(t.getName());
		}
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return "admin/blogs";
	}
	
	@PostMapping("/blogs/search")
	public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable, 
			BlogQuery blog, Model model) {
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return "admin/blogs :: blogList";
	}
}
