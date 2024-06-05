package com.exrestapi2.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exrestapi2.demo.entity.Memo;

public interface MemoRepository extends JpaRepository<Memo, Integer>{

}
