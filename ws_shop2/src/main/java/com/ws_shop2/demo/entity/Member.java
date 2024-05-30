package com.ws_shop2.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="member")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mno;
	
	@Column(unique = true)
	private String username;
	
	@Column(nullable = false)
	private String pw;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String role;
}
