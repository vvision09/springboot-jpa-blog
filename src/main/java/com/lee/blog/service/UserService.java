package com.lee.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void save(User user) {
		String rawPassword = user.getPassword(); //원문
		String encPassword = encoder.encode(rawPassword);
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
	}

	/* 로그인
	@Transactional(readOnly = true)	//SELECT 할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
	public User login(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());
	}
	*/
}
