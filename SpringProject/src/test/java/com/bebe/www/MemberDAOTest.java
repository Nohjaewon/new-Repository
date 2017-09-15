package com.bebe.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bebe.domain.MemberVO;
import com.bebe.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class MemberDAOTest{
	@Inject
	private MemberDAO dao;
	
	
	public void testTime() throws Exception{
		System.out.println(dao.getTime());
	}
	public void testInsertMember() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user1");
		vo.setUserpw("user1");
		vo.setUsername("user1");
		vo.setEmail("user1");
		dao.insertMember(vo);
	}
	@Test
	public void testReadMember() throws Exception{
		System.out.println(dao.readMember("user1"));
	}
	@Test
	public void testReadwithPW() throws Exception{
		MemberVO vo = new MemberVO();
		vo.setUserid("user1");
		vo.setUserpw("user1");
		if(dao.readwithPW(vo)!=null) {
			System.out.println("로그인에성공해땅");
		}
		else {
			System.out.println("로그인에실패해땅");
		}
	}
}