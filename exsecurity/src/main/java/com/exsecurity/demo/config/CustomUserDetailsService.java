package com.exsecurity.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exsecurity.demo.entity.Member;
import com.exsecurity.demo.repository.MemberRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	//username = id 규칙
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Member member = memberRepo.findByUsername(username);
		
		if(member != null) {
			return new CustomUserDetails(member);
		}
		return null;
	}

}
