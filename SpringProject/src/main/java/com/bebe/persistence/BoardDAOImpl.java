package com.bebe.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bebe.domain.BoardVO;
import com.bebe.page.Criteria;
import com.bebe.page.Search;

@Repository
public class BoardDAOImpl implements BoardDAO{
	@Inject
	private SqlSession sqlSession;
	
	private String namespace = "BoardMapper";
	
	@Override
	public void create(BoardVO vo) {
		sqlSession.insert(namespace+".insertBoard", vo);
	}
	@Override
	public BoardVO read(int bno) {
		return sqlSession.selectOne(namespace+".readBoard", bno);
	}
	@Override
	public void delete(int bno) {
		sqlSession.delete(namespace+".deleteBoard", bno);
	}
	@Override
	public List<BoardVO> listAll(){
		return sqlSession.selectList(namespace+".listAll");
	}
	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update(namespace+".updateBoard", vo);
	}
	@Override
	public List<BoardVO> listpage(int page) throws Exception {
		if(page==0)
		page = 1;
		page = (page-1)*10;
		return sqlSession.selectList(namespace+".listpage", page);
	}
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception{
		return sqlSession.selectList(namespace+".listCriteria", cri);
	}
	@Override
	public int totalCount() {
		return sqlSession.selectOne(namespace+".totalCount");
	}
	@Override
	public List<BoardVO> listSearch(Search cri) throws Exception{
		return sqlSession.selectList(namespace+".listSearch", cri);
	}
	@Override
	public int searchTotal (Search cri) throws Exception{
		return sqlSession.selectOne(namespace+".searchTotal", cri);
	}
}
