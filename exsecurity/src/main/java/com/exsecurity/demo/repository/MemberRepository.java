package com.exsecurity.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exsecurity.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByUsername(String username);
	
}
