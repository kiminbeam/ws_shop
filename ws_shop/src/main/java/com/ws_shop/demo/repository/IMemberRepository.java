package com.ws_shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop.demo.entity.Member;

public interface IMemberRepository extends JpaRepository<Member, String>{

}
