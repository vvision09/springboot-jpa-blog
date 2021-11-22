package com.lee.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {
	
	@GetMapping({"","/"})
	public String index() {	
		//컨트롤러에서 세션을 어떻게 찾는지
		//@AuthenticationPrincipal PrincipalDetail principal 를 파라미터로 넣는다.
		return "index"; 
	}
	
	//USER 권한이 필요
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}
	
}
