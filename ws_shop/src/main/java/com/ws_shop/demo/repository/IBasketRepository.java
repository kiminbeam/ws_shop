package com.ws_shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ws_shop.demo.entity.Basket;

public interface IBasketRepository extends JpaRepository<Basket, Long>{
	
	@Query(value="select * from basket where id= :id", nativeQuery=true)
	public List<Basket> findBymemberId(@Param("id") String id);
}
