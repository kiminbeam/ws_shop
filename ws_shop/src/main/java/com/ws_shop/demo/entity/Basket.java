package com.ws_shop.demo.entity;

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
@Table(name = "basket")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Basket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bnum;
	
	@ManyToOne
	@JoinColumn(name = "id")
	private Member member;
	
	@ManyToOne
	@JoinColumn(name = "picknum")
	private Pick pick;
	
	@ManyToOne
	@JoinColumn(name = "gid")
	private Goods goods;
	
}
