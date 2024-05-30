package com.ws_shop.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ws_shop2.demo.entity.Member;
import com.ws_shop2.demo.repository.IMemberRepository;

@SpringBootTest
public class TestEntity {
	
	@Autowired
	IMemberRepository memre;
	
	//@Test
	public void insertdummis() {
		Member member = new Member();
		member.setId("aaaa");
		member.setPw("1111");
		member.setRole("ROLE_member");
		memre.save(member);
	}
	
	@Test
	public void findByIdPw() {
		Member member = memre.findByIdPw("abcd", "1234");
		System.out.println(member.toString());
	}
}
