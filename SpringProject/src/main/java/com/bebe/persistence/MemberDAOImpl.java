package com.bebe.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bebe.domain.BoardVO;
import com.bebe.domain.MemberVO;
import com.bebe.page.Search;

@Repository
public class MemberDAOImpl implements MemberDAO{
	@Inject
	private SqlSession sqlSession;
	
	private static String namespace = "MemberMapper";
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace+".getTime");
	}
	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace+".insertMember", vo);
	}
	@Override
	public MemberVO readMember(String userid) throws Exception{
		return sqlSession.selectOne(namespace+".selectMember", userid);
	}
	@Override
	public MemberVO readwithPW(MemberVO vo)throws Exception{
		return sqlSession.selectOne(namespace+".readWithPW", vo);
	}
}
