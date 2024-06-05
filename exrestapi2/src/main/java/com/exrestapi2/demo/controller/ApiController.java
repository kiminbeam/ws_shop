package com.exrestapi2.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exrestapi2.demo.dto.LoginDto;
import com.exrestapi2.demo.entity.Login;
import com.exrestapi2.demo.repository.LoginRepository;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	private Dash dash; //생성자 주입방식 >> autowired 필요 X
	private int cnt = 0;
	private int cnt2 = 0;
	
	public ApiController(Dash dash) {
		this.dash = dash;
	}
	
	/*
	@PostMapping("/submitForm")
	public String myForm(@RequestParam Map<String, String> formData) {
		String username = formData.get("username");
		String password = formData.get("password");
		
		return username + "," + password;
		
	}
	*/
	
	@PostMapping("/submitForm")
	public String myForm(@RequestParam("username") String username, @RequestParam("password") String password) {
		//String uName = username;
		//String pw = password;
		cnt++;
		dash.setC1(cnt);
		System.out.println(cnt);
		return username + "," + password;
		
	}
	
	/*
	@PostMapping("/submitJson")
	public String submitJson(@RequestBody Map <String, String> jsonData) {
		String name = jsonData.get("username");
		String password = jsonData.get("password");
		
		return "JSON submit successfully: " + name + "," + password;
	}
	*/
	
	@PostMapping("/submitJson")
	public String submitJson(@RequestBody LoginDto loginDto) {
		cnt2++;
		dash.setC2(cnt2);
		System.out.println(cnt2);
		String username = loginDto.getUsername();
		String password = loginDto.getPassword();
		
		return "JSON submit successfully: " + username + "," + password;
	}
	
	@Autowired
	LoginRepository loginRepo;
	
	@GetMapping("/checkId")
	public String checkId(@RequestParam("username") String username) {
		Login loginInfo = loginRepo.findbyUsername(username);
		
		if(loginInfo.getUsername() == username ) {
			return "사용가능한 아이디 입니다.";
		}else {
			return "중복된 아이디가 존재합니다.";
		}
		 
	}
}
