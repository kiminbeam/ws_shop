package com.ws_shop2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop2.demo.entity.Basket;

public interface IBasketRepository extends JpaRepository<Basket, Long>{

}
