package com.exjwt.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exjwt.demo.entity.Member;
import com.exjwt.demo.repository.MemberRepository;

@CrossOrigin("*")
@RestController
public class MainController {
	
	@Autowired
	private MemberRepository memberRepo;
	
	
	@RequestMapping("/joinProc")
	public String joinProc(Member member) {
		member.setRole("ROLE_MEMBER");
		
		//DB 저장
		Member result = memberRepo.save(member);
		
		if(result != null) {
			return "등록 성공";
		}else {
			return "등록 실패";
		}
	}
	
	
	
	
}
