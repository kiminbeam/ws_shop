package com.news.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NewsDto {
	private Long nno;
	private String writer;
	private String headline;
	private String content;
	private Date date;
	private int status;
}
