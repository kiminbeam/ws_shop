package com.exjquery.demo.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private Dash dash;
	private int cnt = 0;
	private int cnt2 = 0;
	
	public ApiController(Dash dash) {
		this.dash = dash;
	}

	@PostMapping("/submitForm")
	public String myForm(@RequestParam Map<String, String> formData) {
		cnt++;
		dash.setC1(cnt);
		
		String username = formData.get("username");
		String password = formData.get("password");
		
		return username + "," + password;
		
	}
	
	@PostMapping("/submitJson")
	public String submitJson(@RequestBody Map <String, String> jsonData) {
		cnt2++;
		dash.setC2(cnt2);
		
		String name = jsonData.get("username");
		String password = jsonData.get("password");
		
		return "JSON submit successfully: " + name + "," + password;
	}
}
