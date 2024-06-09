package com.exjwt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exjwt.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	
	public Member findByUsernameAndPassword(String username, String password);
}
