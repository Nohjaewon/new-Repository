package com.bebe.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bebe.domain.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{
	
	@Inject
	private SqlSession sql;
	private static String namespace = "ReplyMapper";
	
	@Override
	public List<ReplyVO> list(int bno) throws Exception {
		return sql.selectList(namespace+".list", bno);
	}

	@Override
	public void create(ReplyVO vo) throws Exception {
		sql.insert(namespace+".create", vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		sql.update(namespace+".update", vo);
	}

	@Override
	public void delete(int rno) throws Exception {
		sql.delete(namespace+".delete", rno);
	}
	
}
