package com.exsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//패스워드 암호화 위한 메서드
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//인가 설정위한 메서드
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/","/joinForm", "/registProc").permitAll()
				.requestMatchers("/members/**").hasAnyRole("ADMIN","MEMBER")
				.requestMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				);
		
		//내가 직접만든 로그인 페이지로 보내는 것
		http.formLogin((auth) -> auth
				.loginPage("/login") //로그인 폼 지정 - 쓰지않으면 Spring Security가 제공하는 로그인 폼 사용
				.loginProcessingUrl("/loginProc") //로그인 폼 지정 후 폼 파라미터 보내는 경로지정 - 컨트롤러에 직접 만들지 않는다.(Spring Security가 알아서 처리함)
				.defaultSuccessUrl("/members/")
				.permitAll()
				);
		
		http.csrf(AbstractHttpConfigurer::disable);
		
		return http.build();
	}
}
