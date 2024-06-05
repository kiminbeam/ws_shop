package com.exrestapi.demo.mycontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exrestapi.demo.dto.MemberDto;
import com.exrestapi.demo.entity.Member;
import com.exrestapi.demo.repository.MemberRepository;

@Controller
@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {
	
	//http://localhost:8093/api/v1/post-api/domain
	@PostMapping("/domain")
	//@RequestMapping(value = "/domain", method=RequestMethod.POST)
	public String postExample() {
		return "Hello Post Api";
	}
	
	@PostMapping("/member0")
	public String PostMemberDto0(@RequestParam("name")String name, @RequestParam("email")String email, @RequestParam("addr")String addr ) {
		return name + ", " + email + ", " + addr;
	}
	
	
	//http://localhost:8093/api/v1/post-api/member
	@PostMapping("/member")
	public String postMember(@RequestBody Map<String, Object> postData) {
		StringBuilder sb = new StringBuilder();
		postData.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	
	//http://localhost:8093/api/v1/post-api/member2
	@PostMapping("/member2")
	public String PostMemberDto(@RequestBody MemberDto member) {
		return member.toString();
	}
	
	@PostMapping("/member3")
	public MemberDto PostMemberDto3(@RequestBody MemberDto member) {
		return member;
	}
	
	
	@Autowired
	MemberRepository memberRepo;
	
	//http://localhost:8093/api/v1/post-api/member4
	@PostMapping("/member4")
	public String PostMember4(@RequestBody Member member) {
		memberRepo.save(member);
		
		return member.toString();
	}
	
	
}
