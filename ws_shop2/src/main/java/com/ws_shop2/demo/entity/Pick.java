package com.ws_shop2.demo.entity;

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
@Table(name = "pick")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pick {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long picknum;
	
	@ManyToOne
	@JoinColumn(name= "gid")
	private Goods goods;
	
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Member member;
}
