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

import com.blog.domain.Blog;
import com.blog.domain.Type;
import com.blog.service.BlogService;
import com.blog.service.TagService;
import com.blog.service.TypeService;
import com.blog.vo.BlogQuery;

@Controller
@RequestMapping("/admin")
public class BlogController {
	
	private static final String INPUT = "admin/blogs-input";
	private static final String LIST = "admin/blogs";
	private static final String REDIRECT_LIST = "redirect:/admin/blogs";
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private TypeService typeService;
	
	@Autowired
	private TagService tagService;
	
	@GetMapping("/blogs")
	public String blog(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable, 
			BlogQuery blog, Model model) {
		
		model.addAttribute("types", typeService.listType());
		/*
		 * for (Type t : typeService.listType()) { System.out.println(t.getName()); }
		 */
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return LIST;
	}
	
	@PostMapping("/blogs/search")
	public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Direction.DESC) Pageable pageable, 
			BlogQuery blog, Model model) {
		model.addAttribute("page", blogService.listBlog(pageable, blog));
		return "admin/blogs :: blogList";
	}
	
	@GetMapping("blogs/input")
	public String input(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.listTag());
		model.addAttribute("blog", new Blog());
		return INPUT;
	}
}
