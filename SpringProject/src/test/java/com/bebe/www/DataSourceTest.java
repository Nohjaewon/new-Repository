package com.bebe.www;

import java.sql.Connection; 

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})

public class DataSourceTest{
	@Inject
	/*private DataSource ds;*/
	private SqlSessionFactory sqlFactory;
	
	
	public void testFactory() {
		System.out.println(sqlFactory);
	}
	
	
	/*public void testConnection() throws Exception{
		try {
			Connection conn = ds.getConnection();
				System.out.println(conn);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	public void testSession() throws Exception {
		try (SqlSession session = sqlFactory.openSession()){;
			System.out.println(session);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}