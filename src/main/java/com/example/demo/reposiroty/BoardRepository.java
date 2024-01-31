package com.example.demo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;

// JpaRepository<Entity 클래스, PK 타입(클래스 타입)>
// Entity는 DB 테이블 명칭이다.
// long이면 Long으로 클래스 타입으로 작성해야 됨
public interface BoardRepository extends JpaRepository<Board, Long>{
	// JPA의 repository 패키지는 Mapper의 역할을 하지 않음
}
