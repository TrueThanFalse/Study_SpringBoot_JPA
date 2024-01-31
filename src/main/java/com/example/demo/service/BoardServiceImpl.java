package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.reposiroty.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardRepository BoardRepository;

	@Override
	public Long register(BoardDTO bdto) {
		// TODO Auto-generated method stub

		return BoardRepository.save(convertDtoToEntity(bdto)).getBno();
		// save : INSERT 구문 (저장 후 저장한 Entity 객체를 반환함)
	}

	@Override
	public List<BoardDTO> getListAll() {
		// TODO Auto-generated method stub
		
		// DB에서 Board로 리턴되기 때문에 List<Board>로 받아야 함
		// 그 다음에 BoardDTO로 변환
		
		List<Board> list = BoardRepository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
		// DB에서 가져올 때 정렬해서 가져오기
		List<BoardDTO> dtoList = new ArrayList<>();
		for(Board bvo : list) {
			dtoList.add(convertEntityToDto(bvo));
		}
		return dtoList;
	}
}
