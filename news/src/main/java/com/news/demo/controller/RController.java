package com.news.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.news.demo.dto.NewsDto;
import com.news.demo.entity.News;
import com.news.demo.repository.NewsRepository;

@RestController
@RequestMapping("/api")
public class RController {
	
	@Autowired
	NewsRepository newsRepo;
	
	@PostMapping("/postNews")
	public News postNews(@RequestBody NewsDto newsDto) {
		
		News news = new News();
		news.setWriter(newsDto.getWriter());
		news.setHeadline(newsDto.getHeadline());
		news.setDate(newsDto.getDate());
		news.setContent(newsDto.getContent());
		
		News ns = newsRepo.save(news);
		return ns;
	}
	
	@GetMapping("/confirm")
	public List<News> confirmPage() {
		
		List <News> list= newsRepo.findAll();
		
		return list;
	}
	
	/*
	@GetMapping("/getCfDetail")
	public News detailCf() {
		
	}
	*/
}
