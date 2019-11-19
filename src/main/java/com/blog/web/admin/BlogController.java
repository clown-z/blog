package com.blog.web.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blog.domain.Blog;
import com.blog.domain.Type;
import com.blog.domain.User;
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
//		model.addAttribute("types", typeService.listType());
//		model.addAttribute("tags", tagService.listTag());
		setTypeAndTag(model);
		model.addAttribute("blog", new Blog());
		return INPUT;
	}
	
	public void setTypeAndTag(Model model) {
		model.addAttribute("types", typeService.listType());
		model.addAttribute("tags", tagService.listTag());
	}
	
	@GetMapping("blogs/{id}/input")
	public String editInput(@PathVariable Long id, Model model) {
		setTypeAndTag(model);
		Blog blog = blogService.getBlog(id);
		System.out.println("sdsjh"+blog.toString());
		blog.init();
		model.addAttribute("blog", blog);
//		Blog blog2 =blogService.updateBlo(id, blog);
//		System.out.println("sdsjh2"+blog.toString());
		return INPUT;
	}
	
	@PostMapping("/blogs")
	public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
		blog.setUser((User) session.getAttribute("user"));
		blog.setType(typeService.getType(blog.getType().getId()));
		blog.setTags(tagService.listTag(blog.getTagIds()));
		Blog b = blogService.saveBlog(blog);
		if(b == null) {
			attributes.addFlashAttribute("message", "操作失败");
		}else {
			attributes.addFlashAttribute("message", "操作成功");
		}
		return REDIRECT_LIST;
	}
}
