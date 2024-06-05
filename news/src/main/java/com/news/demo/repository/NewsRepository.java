package com.news.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.news.demo.entity.News;

public interface NewsRepository extends JpaRepository<News, Long>{

}
