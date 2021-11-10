package com.lee.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lee.blog.model.RoleType;
import com.lee.blog.model.User;
import com.lee.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try{
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다. 해당 아이디는 없습니다." ;
		}
		
		return "삭제";
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//save함수는 id를 전달하지 않으면 insert
	//save함수는 id를 전달해서 해당 id에 대한 데이터가 있으면 update
	//save함수는 id를 전달해서 id가 없으면 데이터 insert
	@Transactional //함수 종료시 자동 commit, 더티체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id,@RequestBody User requestUser) {	//JSON 데이터를 요청 -> Java Object(Message Convert가 변환)
		System.out.println("id:" + id);
		System.out.println("password:" + requestUser.getPassword());
		System.out.println("email:" + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("해당 유저는 없습니다. id : " + id); 
		});
		
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
		//userRepository.save(user);
		
		
		return user;
		
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
