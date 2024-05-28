package com.ws_shop.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long gid;
	
	@Column(nullable = false)
	private String gname;
	
	private int amount;
	
	@Column(nullable = false)
	private int state;
	
	@Column(length = 500)
	private String review;
	
	
	private int sales;
	
	@Column(nullable = false)
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Member member;
}
