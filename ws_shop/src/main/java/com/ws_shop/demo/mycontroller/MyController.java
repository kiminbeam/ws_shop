package com.ws_shop.demo.mycontroller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ws_shop.demo.entity.Basket;
import com.ws_shop.demo.entity.Goods;
import com.ws_shop.demo.entity.Member;
import com.ws_shop.demo.entity.Pick;
import com.ws_shop.demo.entity.Receipt;
import com.ws_shop.demo.repository.IBasketRepository;
import com.ws_shop.demo.repository.IGoodsRepository;
import com.ws_shop.demo.repository.IMemberRepository;
import com.ws_shop.demo.repository.IPickRepository;
import com.ws_shop.demo.repository.IReceiptRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class MyController {
	@Autowired
	IMemberRepository memreposi;
	
	@Autowired
	IGoodsRepository goodsRepository;
	
	@Autowired
	IBasketRepository basketRepo;
	
	@Autowired
	IPickRepository pickRepository;
	
	@Autowired
	IReceiptRepository receiptRepo;
	
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
	
	

	@RequestMapping("/pickUp")
	public String goBasket(Pick pick, @RequestParam("gid") Long gid, HttpServletRequest request) {
		Goods goods = goodsRepository.findById(gid).orElse(null);
		
		String id = (String) request.getSession().getAttribute("id");
		Member member = memreposi.findById(id).orElse(null);
		
		if(goods != null && member != null) {
			pick.setMember(member);
			pick.setGoods(goods);
			pickRepository.save(pick);
		}
		
		return "redirect:/mypickup";
	}
	
	@RequestMapping("/mypickup")
	public String mypickup(HttpServletRequest request, Model model) {
		String id = (String) request.getSession().getAttribute("id");
		List<Pick> pickList = pickRepository.findBymemberid(id);
		
		model.addAttribute("pick", pickList);
		
		return "user/mypickup";
	}
	
	//픽업 한 물건들을 최종 장바구니로 옮기는 메서드
	@RequestMapping("/getinBasket")
	public String getinBasket(@RequestParam("picknum") List<Long> picknumList, HttpServletRequest request, @RequestParam("gid") List<Long> gidList,Model model) {
		
		String id = (String) request.getSession().getAttribute("id");
		Member member = memreposi.findById(id).orElse(null);
		
		if(member != null) {
			for(int i = 0; i < picknumList.size(); i++) {
				Pick pick = pickRepository.findById(picknumList.get(i)).orElse(null);
				Goods goods = goodsRepository.findById(gidList.get(i)).orElse(null);
				
				if(pick != null && goods != null) {
					Basket basket = new Basket();
					basket.setMember(member);
					basket.setGoods(goods);
					basket.setPick(pick);
					
					basketRepo.save(basket);
				}
			}
		}
		
		List<Basket> basketList = basketRepo.findBymemberId(id);
		model.addAttribute("basket", basketList);
		
		return "user/mybasket";
	}
	
	
	//메인 페이지의 장바구니 불러오기 메뉴 클릭시 실행되는 메서드 
	@RequestMapping("/mybasket")
	public String gobasket(HttpServletRequest request, Model model) {
		String id = (String) request.getSession().getAttribute("id");
		List <Basket> basketList = basketRepo.findBymemberId(id);
		model.addAttribute("basket", basketList);
		
		return "user/mybasket";
	}
	
	/*
	//장바구니에서 물품 빼는 기능
	@RequestMapping(value = "/removeFromBasket", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> removeFromBasket(@RequestParam("bnum") Long bnum, HttpServletRequest request ,Model model) {
		//관련된 receipt 항목 삭제
		receiptRepo.deleteByBasketBnum(bnum);
		
		//장바구니 항목 삭제 
		basketRepo.deleteById(bnum);
		
		return ResponseEntity.ok("item removed successfully");
	}
	*/
	
	
	@RequestMapping("/deleteOne")
	public String deleteOne(Model model, @RequestParam("bnum") Long bnum, HttpServletRequest request) {
		receiptRepo.deleteByBasketBnum(bnum);
		
		basketRepo.deleteById(bnum);
		
		String id = (String) request.getSession().getAttribute("id");
		Member member = memreposi.findById(id).orElse(null);
		
		if(member != null) {
			List <Basket> basketList = basketRepo.findBymemberId(id);
			model.addAttribute("basket", basketList);
		}
		
		return "user/mybasket";
		
	}
	
	
	
	// @RequestParam("orderDate") String orderDate
	//주문내역으로 전송하는 메서드
	@RequestMapping("/order")
	public String order(HttpServletRequest request, @RequestParam("gid") List<Long> gidList
			, @RequestParam("picknum") List<Long> picknumList
			, @RequestParam("bnum") List<Long> bnumList
			, Model model) {
		
		List<Receipt> receiptList = new ArrayList<>();
		
		String id = (String) request.getSession().getAttribute("id");
		Member member = memreposi.findById(id).orElse(null);
		
		//시간 저장하는 코드
		//LocalDateTime orderTime = LocalDateTime.parse(orderDate,DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		
		for(int i = 0; i < bnumList.size(); i++) {
			Goods goods = goodsRepository.findById(gidList.get(i)).orElse(null);
			Basket basket= basketRepo.findById(bnumList.get(i)).orElse(null);
			
			if(goods != null && basket != null) {
				Receipt receipt = new Receipt();
				receipt.setMember(member);
				receipt.setGoods(goods);
				receipt.setBasket(basket);
				//receipt.setOrderDate(orderDate);
				
				receiptRepo.save(receipt);
				receiptList.add(receipt);
			}
		}
		
		model.addAttribute("receipt", receiptList);
		
		return "user/orderpage";
	}
	
	@RequestMapping("/userReceiptPage")
	public String userReceiptPage(HttpServletRequest request,Model model) {
		String id = (String)request.getSession().getAttribute("id");
		List <Receipt> receiptList = receiptRepo.findAllByMemberId(id);
		
		model.addAttribute("receipt", receiptList);
		
		return "user/orderpage";
	}
	
}
