package com.lee.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.lee.blog.model.User;

//<User,PK>
//DAO
//자동으로 bean 등록이 된다, @Repository 생략 가능 
public interface UserRepository extends JpaRepository<User,Integer> {
	
	
	
	
	
}
//JPA Naming 쿼리
	//SELECT * FROM user WHERE username = ?1 AND password = ?2
	//User findByUsernameAndPassword(String username,String password);
	
	/* 
	 import org.springframework.data.jpa.repository.Query;
	 
	@Query(value="SELECT * FROM user WHERE username = ?1 AND password = ?2",nativeQuery = true)
	User login(String username,String password);
	*/