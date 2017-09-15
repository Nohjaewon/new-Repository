package com.bebe.www;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardTest {

@Test
public void testURI() throws Exception{
	UriComponents uri = UriComponentsBuilder.newInstance().path("sboard/list")
	.queryParam("/page", "1")
	.build();
	
	System.out.println("/sboard/list?page=1");
	System.out.println(uri.toString());
}
}
