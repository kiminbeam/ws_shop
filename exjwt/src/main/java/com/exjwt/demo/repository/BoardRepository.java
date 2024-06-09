package com.exjwt.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exjwt.demo.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
