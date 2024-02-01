package com.example.demo.dto;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
	
	private int pageNo;
	private int totalPage;
	private int startPage;
	private int endPage;
	private boolean hasPrev, hasNext;
	
	public PageVO(Page<BoardDTO> list, int pageNo) {
		// pageNo = 현재 선택한 페이지네이션. => 0부터 시작함
		
		pageNo = pageNo+1;
		this.pageNo = pageNo;
		this.totalPage = list.getTotalPages();
		
		int qty = 10;
		
		this.endPage = (int)Math.ceil(pageNo/(double)qty) * qty;
		this.startPage = endPage - (qty-1);
		
		if(endPage > totalPage) {
			this.endPage = totalPage;
		}
		
		this.hasNext = list.hasNext();
		this.hasPrev = pageNo > 10;
	}
}
