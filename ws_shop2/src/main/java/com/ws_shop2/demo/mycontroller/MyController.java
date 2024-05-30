package com.ws_shop2.demo.mycontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ws_shop2.demo.entity.Goods;
import com.ws_shop2.demo.entity.Member;
import com.ws_shop2.demo.entity.Pick;
import com.ws_shop2.demo.repository.IGoodsRepository;
import com.ws_shop2.demo.repository.IMemberRepository;
import com.ws_shop2.demo.repository.IPickRepository;

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
	public String mainpage(HttpServletRequest request, Model model) {
		List<Goods> goods = goodsRepository.findAll();
		
		model.addAttribute("goods", goods);
		return "/mainPage";
	}
	
	@RequestMapping("/login_page")
	public String loginpage() {
		return "/login_page";
	}
	
	@RequestMapping("/login")
	public String login(Model model, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		Member member = memreposi.findByIdPw(id, pw);
		
		//model.addAttribute("member", member);
		session.setAttribute("member", member);
		session.setAttribute("id", member.getId());
		session.setAttribute("role", member.getRole());
		session.setAttribute("name", member.getName());
		System.out.println(member);
		//request.getSession().setAttribute("name", member.getName());
		//request.getSession().setAttribute("Role", member.getRole());
		
		List<Goods> goodsList = goodsRepository.findAll();
		model.addAttribute("goods", goodsList);
		
		return "/mainpage";
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
	public String mange(Model model,HttpServletRequest request) {
		
		return "/admin/manage";
	}
	
	//제품 등록 폼
	@RequestMapping("/addGoods")
	public String addGoods(Model model,HttpServletRequest request) {
		
		String id = (String)request.getSession().getAttribute("id");
		
		Optional <Member> mem= memreposi.findById(id);
		Member member = mem.get();
		
		System.out.println(member);
		
		model.addAttribute("member", member);
		
		return "/admin/addGoods";
	}
	
		
	@RequestMapping("/addG")
	public String addG(Goods goods,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			return "redirect:/mainpage";
		}
		
		goods.setMember(member);
		goodsRepository.save(goods);
		
		return "redirect:/manage";
	}
	
	//등록제품 조회
	@RequestMapping("/viewMygs")
	public String viewMygs(Model model,HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("id");
		List<Goods> goods= goodsRepository.findByMemberId(id);
		
		for(int i= 0 ; i < goods.size() ; i++) {
			System.out.println(goods);
		}
		
		model.addAttribute("goods", goods);
		
		return "admin/viewMygs";
	}
	
	@RequestMapping("/detailMygs")
	public String detailMygs(Model model,@RequestParam("gid") Long gid, HttpSession session){
		System.out.println("aaaa.a..................................");
		
		String id = (String) session.getAttribute("id");
		Optional<Goods> goods = goodsRepository.findById(gid);
		
		if(goods.isPresent()) {
			Goods gs = goods.get();
			model.addAttribute("goods", gs);
			
			Member mem = gs.getMember();
			model.addAttribute("member", mem);
		}
		
		return "admin/detailMygs";
	}
	
	//제품정보 수정폼
	@RequestMapping("/modifyGs")
	public String modifyGs(Model model, @RequestParam("gid") Long gid, HttpServletRequest request ) {
		String id = (String)request.getSession().getAttribute("id");
		
		Optional<Goods> goods = goodsRepository.findById(gid);
		Goods gs = goods.get();
		model.addAttribute("goods", gs);
		
		Member member = gs.getMember();
		model.addAttribute("member", member);
		
		return "admin/modifyGs";
	}
	
	//제품정보 수정 기능
	@RequestMapping("/modifyGoods")
	public String modifyGoods(Goods goods,HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("member");
		
		goods.setMember(member);
		goodsRepository.save(goods);
		
		return "admin/detailMygs";
	}
	
	//일반회원 제품상세페이지
	@RequestMapping("/detailGoods")
	public String detailGoods(@RequestParam("gid") Long gid, Model model) {
		Optional<Goods> goods = goodsRepository.findById(gid);
		Goods gs = goods.get();
		
		model.addAttribute("goods", gs);
		
		return "user/detailGoods";
	}
	
	@Autowired
	IPickRepository pickRepository;

	@RequestMapping("/pickUp")
	public String goBasket(Pick pick, @RequestParam("gid") Long gid, @RequestParam("memberId") String memberid) {
		Goods goods = goodsRepository.findById(gid).orElse(null);
		Member member = memreposi.findById(memberid).orElse(null);
		
		if(goods != null && member != null) {
			pick.setMember(member);
			pick.setGoods(goods);
			pickRepository.save(pick);
		}
		
		return "user/detailGoods";
	}
	
	@RequestMapping("/mypickup")
	public String mypickup(@RequestParam("id")String id, Model model) {
		List<Pick> pickList = pickRepository.findBymemberid(id);
		
		model.addAttribute("pick", pickList);
		
		return"user/mypcikup";
	}
}
