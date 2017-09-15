package com.bebe.www;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bebe.domain.BoardVO;
import com.bebe.page.Criteria;
import com.bebe.page.Search;
import com.bebe.page.pageMaker;
import com.bebe.persistence.BoardDAO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Inject
	private BoardDAO dao;
/*
@RequestMapping("/list")
	public String listAll(@RequestParam int page, Model model) throws Exception{
		model.addAttribute("board", dao.listpage(page));
		return "/board/board";
}*/
@RequestMapping("/list")
public String listCriiteria(@ModelAttribute("cri") Search cri, Model model) throws Exception{
	model.addAttribute("board", dao.listSearch(cri));
	pageMaker pageMaker = new pageMaker();
	pageMaker.setCri(cri);
	
	pageMaker.setTotalPage(dao.searchTotal(cri));
	model.addAttribute("pageMaker", pageMaker);
	model.addAttribute("page", cri);
	return "/board/board";
	}
@RequestMapping("/write")
	public String write() {
	return "/board/write";
	}
@RequestMapping(value="write", method= {RequestMethod.POST})
public String write(BoardVO vo)throws Exception {
	dao.create(vo);
	return "redirect:/board/list";
}
@RequestMapping("/read")
	public String read(@RequestParam int bno,@RequestParam int page, Model model) throws Exception{
		BoardVO vo = dao.read(bno);
		int cnt = vo.getViewcnt();
		vo.setViewcnt(cnt+1);
		dao.update(vo);
		model.addAttribute("page", page);
		model.addAttribute("read", vo);
		return "/board/read";
}
@RequestMapping("/delete")
	public String delete(@RequestParam int bno,@RequestParam int page) throws Exception{
		dao.delete(bno);
		return "redirect:/board/list?page="+page;
}
@RequestMapping("/edit")
	public String edit(@RequestParam int bno,@RequestParam int page, Model model)throws Exception{
		model.addAttribute("read", dao.read(bno));
		model.addAttribute("page", page);
		return "board/edit";
}
@RequestMapping(value="/edit", method= {RequestMethod.POST})
	public String edit(BoardVO vo,@RequestParam int page) throws Exception{
		dao.update(vo);
		return "redirect:/board/read?bno="+vo.getBno()+"&page="+page;
}

}
