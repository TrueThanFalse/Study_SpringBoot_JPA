package com.example.demo.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
// 상속을 위해서 만들어주는 클래스라는 것을 인지
@EntityListeners(value = {AuditingEntityListener.class})
// 반드시 AuditingEntityListener.class를 지정해줘야
// 데이터가 추가되거나 변경될 때 자동으로 감시하여 시간 값을 할당할 수 있다.
// => JpaApplication 클래스에 @EnableJpaAuditing를 적용한 이유이다.
// Auditing : 감시하다 
@Getter
abstract class TimeBased {
	// abstract : 추상 클래스
	// 날짜 시간만 따로 빼서 관리하는 테이블
	
	@CreatedDate // 생성할 때 할당해라
	@Column(name = "reg_at", updatable = false)
	private LocalDateTime regAt;
	
	@LastModifiedDate // 마지막 수정할 때마다 할당해라
	@Column(name = "mod_at")
	private LocalDateTime modAt;
	
	// LocalDateTime : 날짜+시간
	// LocalDate : 날짜만
}
