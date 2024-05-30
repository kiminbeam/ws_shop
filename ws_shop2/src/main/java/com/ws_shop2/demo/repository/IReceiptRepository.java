package com.ws_shop2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop2.demo.entity.Receipt;

public interface IReceiptRepository extends JpaRepository<Receipt, Long>{

}
