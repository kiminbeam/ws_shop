package com.exsecurity.demo.mycontroller;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exsecurity.demo.config.CustomUserDetails;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@RequestMapping("/")
	public String welcome(Model model, Principal principal, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
		System.out.println("admin welcome.............");
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		
		model.addAttribute("username1", userDetails.getUsername());
		model.addAttribute("name1", userDetails.getName());
		
		//Principal 이용
		//Principal.getName()은 userName(id)을 가져온다
		model.addAttribute("username2", principal.getName());
		
		//@AuthenticationPrincipal 이용
		//@AuthenticationPrincipal [객체] {참조주소}
		//@AuthenticationPrincipal [CustomUserDetails] {customUserDetails}
		model.addAttribute("username3", customUserDetails.getUsername());
		model.addAttribute("name3", customUserDetails.getName());
		model.addAttribute("role3", customUserDetails.getRole());
		
		
		return "admin/welcome";
	}
}
