package com.exrestapi.demo.mycontroller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exrestapi.demo.dto.MemberDto;
import com.exrestapi.demo.entity.Member;
import com.exrestapi.demo.repository.MemberRepository;

@RestController
@RequestMapping("/api/v1/put-api")
public class PutController {
	
	//http://localhost:8093/api/v1/put-api/member
	@PutMapping("/member")
	public String putMember(@RequestBody Map<String, Object> putData) {
		StringBuilder sb = new StringBuilder();
		putData.entrySet().forEach(map -> {
			sb.append(map.getKey() + ":" + map.getValue() + "\n");
		});
		
		return sb.toString();
	}
	
	//http://localhost:8093/api/v1/put-api/member2
	@PutMapping("/member2")
	public String putMember2(@RequestBody MemberDto member) {
		return member.toString();
	}
	
	//http://localhost:8093/api/v1/put-api/member3
	@PutMapping("/member3")
	public MemberDto putMember3(@RequestBody MemberDto member) {
		return member;
	}
	
	//http://localhost:8093/api/v1/put-api/member4
	@PutMapping("/member4")
	public ResponseEntity<MemberDto> putMember4(@RequestBody MemberDto memberDto){
		return ResponseEntity
				.status(HttpStatus.ACCEPTED) //응답코드
				.body(memberDto);
	}
	
	
	
	
	
	
	@Autowired
	MemberRepository memberRepo;
	
	@PutMapping("/member5")
	public String putMember5(@RequestBody Member member) {
		Optional <Member> updateM = memberRepo.findById(member.getMno());
		Member mem= updateM.get();
		
		mem.setName(member.getName());
		mem.setEmail(member.getEmail());
		mem.setAddr(member.getAddr());
		
		memberRepo.save(mem);
		
		return "수정된 내용" + mem;
	}
	
}
