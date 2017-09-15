package com.bebe.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController3{
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class);
	@RequestMapping("doD")
	public String doD(Model model) {
		logger.info("doDDD() called..!");
		Product product = new Product("radio", 100);
		model.addAttribute(product);
		return "productDetail";
	}
	@RequestMapping("join")
	public String login() {
		return "join";
	}
	@RequestMapping("join1")
	public String join(AddUser user, Model model) {
		model.addAttribute(user);
		return "user";
	}
	public void add(AddUser user) {
		user = user;
	}
}