package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		return BoardRepository.save(convertDtoToEntity(bdto)).getBno();
		// save : INSERT 구문 (저장 후 저장한 Entity 객체를 반환함)
	}

//	@Override
//	public List<BoardDTO> getListAll() {		
//		// DB에서 Board로 리턴되기 때문에 List<Board>로 받아야 함
//		// 그 다음에 BoardDTO로 변환
//		
//		List<Board> list = BoardRepository.findAll(Sort.by(Sort.Direction.DESC, "bno"));
//		// DB에서 가져올 때 정렬해서 가져오기
//		List<BoardDTO> dtoList = new ArrayList<>();
//		for(Board bvo : list) {
//			dtoList.add(convertEntityToDto(bvo));
//		}
//		return dtoList;
//	}
	@Override
	public Page<BoardDTO> getListAll(int page) {		
		// DB에서 Board로 리턴되기 때문에 List<Board>로 받아야 함
		// 그 다음에 BoardDTO로 변환
		
		Pageable pageable = PageRequest.of(page, 10, Sort.by("bno").descending());
		// 페이지 스타트가 1이면 limit 0,10 / 2 => 10,10 / 3 => 20,10
		// Pageable이 알아서 0, 10, 20씩 10씩 올려서 올려서 계산함
		// pageNo은 0부터 시작이라고 생각해야 됨! (중요)
		
		Page<Board> list = BoardRepository.findAll(pageable);
		
		Page<BoardDTO> dtoList = list.map(e -> convertEntityToDto(e));

		return dtoList;
	}

	@Override
	public BoardDTO getDetail(Long bno) {
		Optional<Board> option = BoardRepository.findById(bno);
		/*
		 *  findById : 아이디(PK)를 주면 해당 객체를 반환
		 *  => 리턴타입이 Optional<Board> 타입으로 반환 됨
		 *  => BoardDTO로 변환 필요
		 *  Optional : nullpointException이 발생 방지 가능
		 *  Optional.empty() : null인 경우 확인 가능 (true,false 반환)
		 *  Optional.isPresent() : 값이 존재하는지 확인 (true,false 반환)
		 *  Optional.get() : 내부 값 가져오기
		 */
		
		return option.isPresent() ? convertEntityToDto(option.get()) : null;
	}

	@Override
	public Long modify(BoardDTO bdto) {
		/*
		 * JPA는 Update 기능이 없다.
		 * => 기존의 객체를 가져와서 변경한 후 다시 저장해야됨
		 */
		
		Optional<Board> option = BoardRepository.findById(bdto.getBno());
		if(option.isPresent()) {
			// 기존 객체 가져오기
			Board entity = option.get();
			// 변경 내용 set
			entity.setTitle(bdto.getTitle());
			entity.setContent(bdto.getContent());
			// 다시 저장
			return BoardRepository.save(entity).getBno();
		}else {
			return null;
		}
	}

	@Override
	public void remove(Long bno) {
		BoardRepository.deleteById(bno);
		// deleteById(Id) : 삭제
	}
}
