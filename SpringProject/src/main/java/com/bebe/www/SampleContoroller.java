package com.bebe.www;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bebe.domain.SampleVO;

@RestController
@RequestMapping("/sample")
public class SampleContoroller {
	@RequestMapping("/hello")
	public String hello() {
		return "Hello worldsssssss";
	}
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		SampleVO vo = new SampleVO();
		
		vo.setFirstName("수아");
		vo.setLastName("정");
		vo.setNo(123);
		return vo;
	}
	@RequestMapping("/sendlist")
	public List<SampleVO> sendList(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("수아");
			vo.setLastName("정");
			vo.setNo(i);
			list.add(vo);
		}
		return list;
	}
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendListNot(){
		List<SampleVO> list = new ArrayList<>();
		for(int i=0; i<10; i++) {
			SampleVO vo = new SampleVO();
			vo.setFirstName("수아");
			vo.setLastName("정");
			vo.setNo(i);
			list.add(vo);
		}
		return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
	}
}
