package com.news.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@RequestMapping("/addNews")
	public String addNewsPage() {
		return "addNews";
	}
	
	@RequestMapping("/confirm")
	public String confirmPage() {
		return "confirm";
	}
	
	@RequestMapping("/detailCf")
	public String detailCf(@RequestParam("nno") Long nno) {
		return "detailCf?nno=" + nno;
	}
}
