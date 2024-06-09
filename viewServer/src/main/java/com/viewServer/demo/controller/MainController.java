package com.viewServer.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String root() {
		System.out.println("root......................");
		return "index";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "joinForm";
	}
}
