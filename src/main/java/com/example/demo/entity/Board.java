package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Entity 클래스에 필수
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/*
 * @Table : 테이블이 생성되어 있지 않다면 DB에 클래스명으로 Table 생성함
 * @Table(name="") : Table 이름이 클래스명과 다를 때만 사용
 */
public class Board extends TimeBased{
	// Entity : DB의 테이블 클래스
	// DTO : 객체를 생성하는 클래스 (=VO)
	// TimeBased를 상속 받았으므로 regAt과 modAt이 멤버변수로 들어옴
	
	// 자주 사용하는 코드들(regAt, modAt 같은 모든 곳에서 사용하는 것들)
	// => base class로 별도 관리
	// 나머지는 직접 테이블에서 관리
	
	@Id
	// @Id : BoardRepository에서의 Id와 연동시키는 멤버변수에 적용시켜야 함(기본키)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @GeneratedValue : 필드 값이 수동으로 할당되지 않고 자동으로 생성
	// IDENTITY : Auto_Increment
	private Long bno;
	// repository에서 Long 클래스 타입이므로 여기서도 Long 타입으로 작성
	
	@Column(length = 100, nullable = false)
	// @Column : 컬럼인 것을 인식시키고 컬럼에 대한 설정을 할 수 있다.
	// default 값 설정은 @ColumnDefault 어노테이션이나
	// columnDefinition 속성을 사용하면 된다.
	private String title;
	
	@Column(length = 100, nullable = false)
	private String writer;
	
	@Column(length = 2000, nullable = false)
	private String content;
	
	// @Builder.Default
	// private int point = 0;
	// @Builder.Default : 생성시 초기화 값을 지정하는 방법 : 객체가 생길 때 객체의 기본값 생성
	// @ColumnDefault : 테이블이 생설될 때 기본값으로 할당
}
