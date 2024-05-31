package com.ws_shop.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ws_shop.demo.entity.Receipt;

public interface IReceiptRepository extends JpaRepository<Receipt, Long>{
	
	@Query(value="delete from receipt where bnum= :bnum",nativeQuery=true)
	public void deleteByBasketBnum(@Param("bnum") Long bnum);
	
	@Query(value="select * from receipt where id= :id", nativeQuery=true)
	public List<Receipt> findAllByMemberId(@Param("id") String id);

}
