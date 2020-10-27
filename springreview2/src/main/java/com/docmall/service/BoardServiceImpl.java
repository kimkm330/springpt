package com.docmall.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.docmall.domain.BoardVO;
import com.docmall.util.SearchCriteria;
import com.docmall.persistence.BoardDAO;

@Service //서비스(비지니스로직) 계층에서 사용할 클래스에 적용
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao; //BoardDAOImpl클래스의 객체가 주입됨.
	
	@Override
	public void create(BoardVO vo) throws Exception {
		dao.create(vo);

	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.read(bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		dao.update(vo);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		dao.delete(bno);

	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		
		return dao.listAll();
	}

	@Override
	public void viewIncrement(Integer bno) throws Exception {
		dao.viewIncrement(bno);
		
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

}
