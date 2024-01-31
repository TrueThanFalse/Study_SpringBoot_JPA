package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {
	// Entity : DB의 테이블 클래스
	// DTO : 객체를 생성하는 클래스 (=VO)
	
	private Long bno;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regAt, modAt;
}