package com.exrestapi2.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
	String[] msgs = {"hello", "안녕하세요.", "곤니치와", "니하오"};
	int i = 0;
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	/*
	@RequestMapping("/")
	public String jquery() {
		return "jquery";
	}
	*/
	
	@RequestMapping("/jquery")
	public String jquery(Model model) {
		if( i > 3) {
			i = 0;
		}
		model.addAttribute("greet", msgs[i++]);
		return "jquery";
	}
	
	@RequestMapping("/ajaxex")
	public String ajaxex() {
		return "ajaxex";
	}
}
