package com.ws_shop2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ws_shop2.demo.entity.Member;

public interface IMemberRepository extends JpaRepository<Member, String>{
	
	@Query(value="select * from member where id= :id and pw= :pw", nativeQuery=true)
	public Member findByIdPw(@Param("id") String id, @Param("pw") String pw); 
}
