package com.exrestapi.demo.mycontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exrestapi.demo.dto.MemberDto;
import com.exrestapi.demo.entity.Member;
import com.exrestapi.demo.repository.MemberRepository;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

	//http://localhost:8093/api/v1/get-api/hello
	@GetMapping("/hello")
	public String getHello() {
		
		return "Hello RestAPI";
	}
	
	//http://localhost:8093/api/v1/get-api/name
	@GetMapping("/name")
	public String getName() {
		return "HongGilDong";
	}
	
	@GetMapping("/variable1/{variable}")
	public String getVariable1(@PathVariable("variable") String variable) {
		return variable;
	}
	
	@GetMapping("/variable2/{variable}")
	public String getVariable2(@PathVariable("variable") String var) {
		return var;
	}
	
	//http://localhost:8093/api/v1/get-api/request?name=홍길동&email=aaaa@aaa.com&addr=부산
	@GetMapping("/request")
	public String getRequestParam1(
			@RequestParam("name") String name, 
			@RequestParam("email") String email,
			@RequestParam("addr") String addr) {
		return name + ", " + email + ", " + addr;
	}
	
	//http://localhost:8093/api/v1/get-api/request2?name=Jamse&email=james@aaa.com&addr=서울&nickname=eneryman
	@GetMapping("/request2")
	public String getRequestParam2(@RequestParam Map<String, String> param) {
		
		StringBuilder sb = new StringBuilder();
		param.entrySet().forEach(map -> {
			sb.append(map.getKey() + " : " + map.getValue() + "\n");
		});
		return sb.toString();
	}
	
	//http://localhost:8093/api/v1/get-api/request3?name=홍길동&email=aaaa@aaa.com&addr=부산
	@GetMapping("/request3")
	public String getRequestParam3(MemberDto member) {
		
		return member.toString();
	}
	
	
	
	@Autowired
	MemberRepository memberRepo;
	
	@GetMapping("/request4")
	public String getRequestParam4(@RequestParam("name") String name) {
		Member mem = memberRepo.findByMemberName(name);
		
		return mem.toString(); 
	}
	
}
