package com.ws_shop.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ws_shop.demo.entity.Pick;

public interface IPickRepository extends JpaRepository<Pick, Long>{

}
