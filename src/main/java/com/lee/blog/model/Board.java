package com.lee.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER)	
	@JoinColumn(name="userId")
	private User user;	
	//Many = Board , User = one , 한명의 유저는 여러개의 게시글을 쓸 수 있다.
	//DB는 오브젝트를 저장할 수 없기 때문에 FK를 사용한다. 하지만 자바는 오브젝트를 저장할 수 있다.
	//JPA를 사용하면 Object를 사용할 수 있다.
	//FetchType.EAGER 는 Board를 select할때 무조건 User를 들고오라는 의미임 
	
	@OneToMany(mappedBy = "board",fetch=FetchType.EAGER)	
	private List<Reply> reply;
	//mappedBy : 연관관계의 주인이 아니다 (FK가 아니니까 DB에 컬럼을 만들지 마라)
	//하나의 게시글에 여러 댓글이 달린다. 
	//JoinColumn으로 DB에 넣을 필요는 없다. 넣게 된다면 게시글 한 레코드에 replyId가 여러개 들어와서 1정규화에 위배되기 때문이다. 
	//mappedBy뒤에 적는 건 Reply에 적은 필드명임.
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
