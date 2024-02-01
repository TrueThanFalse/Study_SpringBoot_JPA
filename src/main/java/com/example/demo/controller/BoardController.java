package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.dto.PageVO;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private final BoardService bsv;
	
	@GetMapping("/register")
	public void register() {
		log.info("@@@@@ GET /register Join Success");
	}
	
	@PostMapping("/register")
	public String register(BoardDTO bdto) {
		log.info("@@@@@ POST /register Join Success");
		log.info("@@@@@ bdto >>> ", bdto);
		
		Long bno = bsv.register(bdto);
		log.info("@@@@@ bno >>> ", bno);
		
		return "/index";
	}
	
//	@GetMapping("/list")
//	public void list(Model m) {
//		log.info("@@@@@ GET /list Join Success");
//		
//		List<BoardDTO> list = bsv.getListAll();
//		m.addAttribute("list", list);
//	}
	@GetMapping("/list")
	public void list(Model m,
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo) {
		log.info("@@@@@ GET /list Join Success");
		
		log.info("@@@@@ pageNo >>> " + pageNo);
		int page = (pageNo == 0 ? 0 : pageNo - 1);
		
		Page<BoardDTO> list = bsv.getListAll(page);
		log.info("@@@@@ total Count >>> " + list.getTotalElements()); // 전체 글 수
		log.info("@@@@@ total Page >>> " + list.getTotalPages()); // realEndPage
		log.info("@@@@@ page Number >>> " + list.getNumber()); // 현재 페이지 위치 => pageNo
		log.info("@@@@@ page Qty >>> " + list.getSize()); // 한 페이지의 게시글 수 => qty
		log.info("@@@@@ page next >>> " + list.hasNext()); // 다음 페이지 여부 (boolean)
		log.info("@@@@@ page prev >>> " + list.hasPrevious()); // 이전 페이지 여부 (boolean)
		
		log.info("@@@@@ BoardDTO list >>> " + list.getContent());
		
		PageVO pgvo = new PageVO(list, page);
		
		m.addAttribute("list", list);
		m.addAttribute("pageVo", pgvo);
	}
	
	@GetMapping("/detail")
	public void detail(@RequestParam("bno") Long bno, Model m) {
		log.info("@@@@@ GET /list Join Success");
		BoardDTO bdto = bsv.getDetail(bno);
		m.addAttribute("bdto", bdto);
	}
	
	@PostMapping("modify")
	public String modify(BoardDTO bdto) {
		Long bno = bsv.modify(bdto);
		return "redirect:/board/detail?bno="+bno;
	}
	
	@PostMapping
	public String remove(Long bno) {
		bsv.remove(bno);
		return "redirect:/board/list";
	}
}
