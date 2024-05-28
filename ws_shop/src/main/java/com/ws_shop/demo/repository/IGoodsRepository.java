package com.ws_shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ws_shop.demo.entity.Goods;

public interface IGoodsRepository extends JpaRepository<Goods, Long>{
	
	@Query(value= "select * from goods where id= :id", nativeQuery=true)
	public List<Goods> findByMemberId(@Param("id")String id);
}
