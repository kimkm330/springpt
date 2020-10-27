package com.docmall.persistence;

import java.util.List;

import com.docmall.domain.BoardVO;
import com.docmall.util.SearchCriteria;

public interface BoardDAO {
	
	//글쓰기
	public void create(BoardVO vo) throws Exception;
	
	//게시물 읽기및수정페이지
	public BoardVO read(Integer bno) throws Exception;
	
	//글수정
	public void update(BoardVO vo) throws Exception;
	
	//글삭제
	public void delete(Integer bno) throws Exception;
	
	
	//글목록
	public List<BoardVO> listAll() throws Exception;
	
	//조회수 증가
	public void viewIncrement(Integer bno) throws Exception;
	
	//목록검색기능
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	//게시물 개수(조건)
	public int listSearchCount(SearchCriteria cri) throws Exception;
}
