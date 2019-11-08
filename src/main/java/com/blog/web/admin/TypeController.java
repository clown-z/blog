package com.blog.web.admin;

import javax.annotation.Resource;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blog.service.TypeService;

@Controller
@RequestMapping("/admin")
public class TypeController {
	
	@Resource
	private TypeService typeService;
	
	@GetMapping("/types")
	public String types(@PageableDefault(size = 10, sort = {"id"}, 
					direction = Direction.DESC) Pageable pageable, Model model) {
		model.addAttribute("page", typeService.listType(pageable));
		return "admin/types";
	}
}
