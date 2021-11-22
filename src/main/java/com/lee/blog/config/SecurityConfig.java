package com.lee.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.lee.blog.config.auth.PrincipalDetailService;


@Configuration //빈등록 : 스프링 컨테이너에서 객체를 관리할 수 있는 것
@EnableWebSecurity //컨트롤러 요청 전에 가로챔 (필터링), 시큐리티 필터가 등록
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean		//Ioc , return 되는 값이 Ioc 컨테이너로 들어가서 스프링이 관리하게 된다.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인을 해주고 password를 가로채는데, 
	// 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야 
	// 같은 해쉬로 암호화 해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//반드시 만들어 줘야 패스워드 비교가 된다.
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()	//csrf 토큰 비활성화 (테스트시 걸어주는 게 좋음)
			.authorizeRequests() //Request가 들어올때
				.antMatchers("/","/auth/**","/js/**","/css/**","/image/**") 
				.permitAll()	// 위에 적어준 모든 페이지는 허용한다.
				.anyRequest()	//auth가 아니라면
				.authenticated()	//인증을 해야한다.
			.and()	//인증이 되지 않은 모든 요청은
				.formLogin()	//로그인 폼으로 간다. 
				.loginPage("/auth/loginForm") //권한이 없는 곳에 접근하면  해당 로그인 페이지로 보낸다.
				.loginProcessingUrl("/auth/loginProc")
				.defaultSuccessUrl("/") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인을 함
				.failureUrl("/auth/loginForm");
	}	
}
