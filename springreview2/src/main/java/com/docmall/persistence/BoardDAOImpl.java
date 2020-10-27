package com.docmall.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.docmall.domain.BoardVO;
import com.docmall.util.SearchCriteria;

@Repository //db와 직접적인 기능을 담당하는 클래스에 적용
public class BoardDAOImpl implements BoardDAO {

	@Inject //스프링컨테이너의 등록된 객체를 주입받는 대상에 사용. 
	private SqlSession session; //root-context.xml의 SqlSessionTemplate클래스의 sqlSession 객체주입을 받음
	
	private static String namespace = "com.docmall.mapper.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		
		session.insert(namespace + ".create", vo);

	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".read", bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(namespace + ".update", vo);

	}

	@Override
	public void delete(Integer bno) throws Exception {
		session.delete(namespace + ".delete", bno);

	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listAll");
	}

	@Override
	public void viewIncrement(Integer bno) throws Exception {
		
		session.update(namespace + ".viwecnt", bno);
		
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(namespace + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(namespace + ".listSearchCount", cri);
	}

	

}
