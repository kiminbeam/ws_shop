package com.exjwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exjwt.demo.entity.Member;
import com.exjwt.demo.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class LoginController {
	
	@Autowired
	private MemberRepository memberRepo; 
	
	//@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@PostMapping("/loginProc")
	public String loginProc(@RequestParam("username") String username, @RequestParam("password")String password) {
		
		//DB에서 아이디, 패스워드 있는지 확인 
		Member member = memberRepo.findByUsernameAndPassword(username, password);
		if(member != null) {
			return "로그인 성공";
		}else {
			return "로그인 실패";
		}
		
	}
}
