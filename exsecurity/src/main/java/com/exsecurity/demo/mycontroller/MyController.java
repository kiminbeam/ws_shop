package com.exsecurity.demo.mycontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exsecurity.demo.dto.MemberDto;
import com.exsecurity.demo.entity.Member;
import com.exsecurity.demo.repository.MemberRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MyController {
	//시큐리티 버전 = 3 
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@RequestMapping("/")
	public String root() {
		log.info("root..................");
		return "index";
	}
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		log.info("joinForm...................");
		return "joinForm";
	}
	
	@RequestMapping("/registProc") // 커맨드 객체
	public @ResponseBody String registProc(MemberDto memberDto) {
		log.info("registProc......." + memberDto);
		
		Member member = new Member();
		member.setUsername(memberDto.getUsername());
		
		//String newPw = 암호화(기존패스워드);
		//member.setPassword(암호화된 패스워드);
		
		String newPw = bCryptPasswordEncoder.encode(memberDto.getPassword());
		member.setPassword(newPw);
		
		member.setName(memberDto.getName());
		member.setRole("ROLE_MEMBER");
		
		//memberRepo.save(엔티티);
		memberRepo.save(member);
		return "회원가입 완료됨.";
	}
	
	@RequestMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	@RequestMapping("/success")
	public String success() {
		return "success";
	}
	
}
