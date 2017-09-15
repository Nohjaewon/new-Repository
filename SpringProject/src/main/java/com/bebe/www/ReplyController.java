package com.bebe.www;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bebe.domain.ReplyVO;
import com.bebe.persistence.ReplyDAO;

@RestController
@RequestMapping("/replies")
public class ReplyController {
	@Inject
	private ReplyDAO dao;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		try {
			dao.create(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		try {
			entity = new ResponseEntity<List<ReplyVO>>(dao.list(bno), HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	@RequestMapping(value="/delete/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable int rno){
		ResponseEntity<String> entity = null;
		try {
			dao.delete(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}
		catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
