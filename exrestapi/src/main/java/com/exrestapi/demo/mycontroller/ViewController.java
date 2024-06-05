package com.exrestapi.demo.mycontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
	
	@RequestMapping("/input")
	public String input() {
		return "/input";
	}
	
	
	
}
