package com.lee.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration //빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있는 것
@EnableWebSecurity //컨트롤러 요청 전에 가로챔 (필터링), 시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean		//Ioc , return 되는 값이 Ioc 컨테이너로 들어가서 스프링이 관리하게 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//csrf 토큰 비활성화 (테스트시 걸어주는 게 좋음)
			.authorizeRequests() //Request가 들어올때
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") 
				.permitAll()	//auth로 들어오면 누구나 허용
				.anyRequest()	//auth가 아니라면
				.authenticated()	//인증을 해야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");	//권한이 없는 곳에 접근하면  해당 로그인 페이지로 보낸다.
	}
}
