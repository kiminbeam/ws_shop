package com.exrestapi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exrestapi.demo.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	@Query(value="select * from member where name= :name",nativeQuery=true)
	public Member findByMemberName(@Param("name") String name);
}
