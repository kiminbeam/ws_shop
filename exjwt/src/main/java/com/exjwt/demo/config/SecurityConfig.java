package com.exjwt.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//빈 어노테이션 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf(auth -> auth.disable());
		
		http.httpBasic(auth -> auth.disable());
		
		http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		
		return http.build();
	}
}
