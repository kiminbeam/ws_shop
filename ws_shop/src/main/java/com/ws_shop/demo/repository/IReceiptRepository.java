package com.ws_shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop.demo.entity.Receipt;

public interface IReceiptRepository extends JpaRepository<Receipt, Long>{

}
