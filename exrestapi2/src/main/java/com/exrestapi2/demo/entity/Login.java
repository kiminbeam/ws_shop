package com.exrestapi2.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Login {
	
	@Id
	private String username;
	
	private String password;
}
