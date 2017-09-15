package com.bebe.persistence;

import com.bebe.domain.MemberVO;

public interface MemberDAO {
	public String getTime();
	public void insertMember(MemberVO vo);
	public MemberVO readMember(String userid) throws Exception;
	public MemberVO readwithPW(MemberVO vo) throws Exception;
}
