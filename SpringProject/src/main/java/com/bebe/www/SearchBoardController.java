package com.bebe.www;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bebe.page.Criteria;
import com.bebe.page.Search;
import com.bebe.page.pageMaker;
import com.bebe.persistence.BoardDAO;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {

@Inject
private BoardDAO dao;
@RequestMapping("/list")
public String listCriiteria(Search cri, Model model) throws Exception{
	model.addAttribute("board", dao.listCriteria(cri));
	pageMaker pageMaker = new pageMaker();
	pageMaker.setCri(cri);
	
	pageMaker.setTotalPage(dao.totalCount());
	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("page", cri);
	return "/sboard/board";
}
}
