package com.lee.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 150)
	private String title;
	
	@Lob //대용량 데이터
	private String content;	//섬머노트 라이브러리 <html> 태그가 섞여서 디자인, 글자용량이 매우 커짐
	
	@ColumnDefault("0")	//숫자는 홑따옴표가 필요업다.
	private int count; //조회수
	
	@ManyToOne	//Many = Board , User = one , 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	@JoinColumn(name="userId")
	private User user;	
	//DB는 오브젝트를 저장할 수 없기 때문에 FK를 사용한다. 하지만 자바는 오브젝트를 저장할 수 있다.
	//JPA를 사용하면 Object를 사용할 수 있다.
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
