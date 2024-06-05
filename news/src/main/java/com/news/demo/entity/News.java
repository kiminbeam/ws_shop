package com.news.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long nno;
	
	@Column(name="writer")
	private String writer;
	
	@Column(name="headline")
	private String headline;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date")
	private Date date;
	
	@Column(name="status")
	private int status = 0;
}
