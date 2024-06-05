package com.exrestapi2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exrestapi2.demo.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String>{

	@Query(value = "select * from Login where username= :username",nativeQuery=true)
	public Login findbyUsername(@Param("username") String username);
}
