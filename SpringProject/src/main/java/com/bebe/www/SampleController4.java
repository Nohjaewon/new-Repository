package com.bebe.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4{
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	AddUser user ;
	@RequestMapping("doE")
	public String doE(RedirectAttributes msg) {
		msg.addFlashAttribute("msg", "12312312");
		return "redirect:/doF";
	}
	@RequestMapping("doF")
	public String doF(@ModelAttribute String msg) {
		return "result";
	}
}