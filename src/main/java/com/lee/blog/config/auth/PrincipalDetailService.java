package com.lee.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username,password 2개를 가로채는데,
	// password 처리는 알아서 함
	// username이 DB에 있는지만 확인해주면 된다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
									.orElseThrow(()->{
										return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);
									});
		return new PrincipalDetail(principal);	// 시큐리티 세션에 유저 정보가 저장된다.
		// UserDetailsService를 구현안하고 그냥 new PrincipalDetail() 을 해주면 기본적으로 
		// 스프링이 제공해주는 Username : user, Password : 콘솔창에 나오는 해쉬화된 암호로 세션에 저장된다.
	}
	
}
