package com.lee.blog.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//스프링이 com.lee.blog 패키지 이하를 스캔해서 모든 파일을 메모리에 new 하지 않고,
//특정 어노테이션이 붙어있는 클래스 파일들을 new(IOC)해서 스프링 컨테이너에서 관리
@RestController
public class BlogControllerTest {
	@GetMapping("/test/hello")
	public String hello() {
		return "<h1>hello spring boot</h1>"; 
	}
}
