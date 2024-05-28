package com.ws_shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop.demo.entity.Basket;

public interface IBasketRepository extends JpaRepository<Basket, Long>{

}
