package com.lee.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//한페이지당 2건의 데이터를 리턴
	//페이지는 0부터 시작
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2,sort="id",direction = Sort.Direction.DESC) Pageable pageable){
		Page<User> pagingUsers = userRepository.findAll(pageable);
		
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	//{id} 주소로 파라미터 전달 받을 수 있다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//return type은 Optional이다.
		//데이터베이스에서 못찾을때 null이 되는데, 처리를 해주기 위해서 Optional로 감싸서 가져오게 됨 
		//id와 매칭되는 user가 있다면 그 user를 반환하고, 아니면 500에러를 발생시킴
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		
		//요청 : 웹브라우저
		//user 객체 = 자바 Object
		//변환 (웹브라우저가 이해할 수 있는 데이터) -> JSON 
		
		return user;
	}
	
	//변수명을 제대로 적으면 RequestParam을 적지 않아도 알아서 들어온다.
	@PostMapping("/dummy/join")
	public String join(User user) {	//key=value(약속된 규칙)
		System.out.println(user);
		System.out.println("username:" + user.getUsername());
		System.out.println("password:" + user.getPassword());
		System.out.println("email:" + user.getEmail());
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
		
		return "회원가입 완료";
	}
}
