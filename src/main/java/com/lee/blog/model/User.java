package com.lee.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity	//User 클래스 Mysql에 테이블이 자동 생성
//@DynamicInsert : insert 시 null 필드 제외 
public class User {
	
	@Id	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)	//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스,auto_increment
	
	@Column(nullable=false,length = 30,unique = true)
	private String username; //아이디
	
	@Column(nullable=false,length = 100) //패스워드를 해쉬로 변경하여 암호화하기 위하여
	private String password; 
	
	@Column(nullable=false,length = 50)
	private String email;
	
	//@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)	
	private RoleType role;
	
	//Enum타입이 String인걸 명시
	//Enum을 쓰는게 좋다. Enum을 쓰면 어떤 데이터의 도메인을 만들어준다. // admin,user,manager
	//Type을 String으로 두면 정해진 것 이외에 값이 들어가기 때문에 좋지 않다. 
	// 도메인은 어떤 범위가 정해졌다는 것 
	
	@CreationTimestamp //자바에서 현재 시간 자동입력
	private Timestamp createDate;
}
