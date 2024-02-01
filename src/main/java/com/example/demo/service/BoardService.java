package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;

public interface BoardService {
	// 인터페이스지만 객체를 생성할 수 있다.
	
	Long register(BoardDTO bdto);

//	List<BoardDTO> getListAll();
	Page<BoardDTO> getListAll(int page);
	
	BoardDTO getDetail(Long bno);
	
	Long modify(BoardDTO bdto);
	
	// DB와 컨트롤러간의 Board <=> BoardDTO 변환이 필요함
		default Board convertDtoToEntity(BoardDTO bdto) {
			// BoardDTO 객체를 DB(Entity 객체 = Board)에 저장하기 위해 변환
			return Board.builder()
					.bno(bdto.getBno())
					.title(bdto.getTitle())
					.writer(bdto.getWriter())
					.content(bdto.getContent())
					.build();
		}
		default BoardDTO convertEntityToDto(Board board) {
			// DB(Entity 객체 = Board)에서 가져온 데이터를 BoardDTO 객체로 변환
			return BoardDTO.builder()
					.bno(board.getBno())
					.title(board.getTitle())
					.writer(board.getWriter())
					.content(board.getContent())
					.regAt(board.getRegAt())
					.modAt(board.getModAt())
					.build();
		}

		void remove(Long bno);
}
