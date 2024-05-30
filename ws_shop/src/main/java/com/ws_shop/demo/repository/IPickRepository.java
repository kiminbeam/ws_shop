package com.ws_shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ws_shop.demo.entity.Pick;

public interface IPickRepository extends JpaRepository<Pick, Long>{
	
	@Query(value="select * from pick where id= :id", nativeQuery=true)
	List<Pick> findBymemberid(@Param("id") String id);
}
