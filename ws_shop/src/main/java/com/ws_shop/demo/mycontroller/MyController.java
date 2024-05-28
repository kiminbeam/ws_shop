package com.ws_shop.demo.mycontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ws_shop.demo.entity.Goods;
import com.ws_shop.demo.entity.Member;
import com.ws_shop.demo.repository.IGoodsRepository;
import com.ws_shop.demo.repository.IMemberRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {
	@Autowired
	IMemberRepository memreposi;
	
	@Autowired
	IGoodsRepository goodsRepository;
	
	@RequestMapping("/")
	public String mainpage(Model model,HttpServletRequest request) {
		String memberId = (String) request.getSession().getAttribute("id");
		if(memberId != null) {
			Optional<Member> memberOpt = memreposi.findById(memberId);
			if(memberOpt.isPresent()) {
				model.addAttribute("member", memberOpt.get());
			}
		}
		return "/mainPage";
	}
	
	@RequestMapping("/login_page")
	public String loginpage() {
		return "/login_page";
	}
	
	@RequestMapping("/login")
	public String login(@RequestParam("id") String id,@RequestParam("pw") String pw, Model model, HttpServletRequest request) {
		Optional<Member> member = memreposi.findById(id);
		Member mem = member.get();
		
		if(pw.equals(mem.getPw())) {
			model.addAttribute("member", mem);
			request.getSession().setAttribute("member",mem);
			return "/mainPage";
		}
		return "/loginFalse";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	//회원가입
	@RequestMapping("/joinclub")
	public String joinclub() {
		return "/joinclub";
	}
	
	@RequestMapping("/doubleIdCheck")
	public @ResponseBody String doubleIdCheck(@RequestParam("id")String id) {
		Optional<Member> result = memreposi.findById(id);
		String msg ="";
		System.out.println(result);
		if(result.isPresent()) {
			msg = "중복된 아이디입니다.";
		}else {
			msg = "사용가능한 아이디입니다.";
		}
		return msg;
	}
	
	@RequestMapping("/join")
	public String join(Member member) {
		memreposi.save(member);
		return "redirect:/login_page";
	}
	
	@RequestMapping("/manage")
	public String mange(Model model, @RequestParam("id")String id) {
		Optional<Member> member = memreposi.findById(id);
		model.addAttribute("member", member);
		
		Member mem= member.get();
		return "/admin/manage?id=" + mem.getId();
	}
	
	@RequestMapping("/addGoods")
	public String addGoods(Model model,@RequestParam("id")String id) {
		Optional<Member> member= memreposi.findById(id);
		
		model.addAttribute("member", member);
		return "admin/addGoods";
	}
	
		
	@RequestMapping("/addG")
	public String addG(Goods goods) {
		goodsRepository.save(goods);
		
		return "redirect:/admin/manage";
	}
	
	@RequestMapping("/viewMygs")
	public String viewMygs(Model model, @RequestParam("id")String id) {
		
		List<Goods> goods= goodsRepository.findByMemberId(id);
		
		model.addAttribute("goods", goods);
		
		return "admin/viewMygs";
	}
}
