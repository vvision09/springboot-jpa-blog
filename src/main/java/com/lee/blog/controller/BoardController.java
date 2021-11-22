package com.lee.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lee.blog.model.Board;
import com.lee.blog.service.BoardService;


@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	
	//컨트롤러에서 세션을 어떻게 찾는지
	//@AuthenticationPrincipal PrincipalDetail principal 를 파라미터로 넣는다.
	@GetMapping({"","/"})
	public String index(Model model,@PageableDefault(size=3,sort="id",direction = Sort.Direction.DESC) Pageable pageable) {	
		model.addAttribute("boards",boardService.글목록(pageable));
		return "index"; 	//viewResolver 작동
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
