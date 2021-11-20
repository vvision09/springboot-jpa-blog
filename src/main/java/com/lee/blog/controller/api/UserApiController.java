package com.lee.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lee.blog.dto.ResponseDto;
import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	//회원가입
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : save 호출");
		user.setRole(RoleType.USER);
		userService.save(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	
	/*
	//로그인
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
		System.out.println("UserApiController : login 호출");
		User principal = userService.login(user); //principal (접근주체)
		
		if(principal != null) {
			session.setAttribute("principal", principal);
		}
		return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
	}
	*/
}
