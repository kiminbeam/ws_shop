package com.ws_shop2.demo.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ws_shop2.demo.entity.Member;

public class CustomUserDetails implements UserDetails{
	
	private Member member;
	
	public CustomUserDetails(Member member) {
		this.member = member;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return member.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		return member.getPw();
	}
	
	@Override
	public String getUsername() {
		
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
