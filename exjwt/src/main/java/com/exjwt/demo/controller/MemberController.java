package com.exjwt.demo.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exjwt.demo.dto.BoardDto;
import com.exjwt.demo.entity.Board;
import com.exjwt.demo.entity.Member;
import com.exjwt.demo.repository.BoardRepository;

import net.coobird.thumbnailator.Thumbnails;

@CrossOrigin("*")
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Value("${spring.servlet.multipart.location}")
	String uploadPath;
	
	
	//@Autowired
	private BoardRepository boardRepo;
	
	public MemberController(BoardRepository boardRepo) {
		this.boardRepo = boardRepo;
	}
	
	//로그인 끝난 상태 = 서비스를 이용하는 단계(멤버메인페이지) -> 게시물을 보거나 등록하는 것.
	//@GetMapping("/main")
	public String main() {
		return "member/main";
	}
	
	//게시물 등록(등록 폼, 등록처리)
	//@GetMapping("/regBoard")
	public String regBoard() {
		return "member/regBoardForm";
	}
	
	@PostMapping("/regBoardProc")
	public @ResponseBody String regBoardProc(BoardDto boardDto) {
		
		Board board = new Board();
		board.setTitle(boardDto.getTitle());
		board.setContent(boardDto.getContent());
		
		//작성자 데이터 타입불일치 해결
		Member member = new Member();
		member.setUsername(boardDto.getWriter());
		
		board.setMember(member);
		
		//파일 관련 멤버변수 세팅
		String originName = boardDto.getFileName();
		board.setOriginName(originName);
		String newName = UUID.randomUUID().toString() + originName; //중복되지 않는 새이름 생성
		board.setNewName(newName);
		
		
		//파일처리
		File file = new File(newName);
		
		try {
			boardDto.getFile().transferTo(file);
			System.out.println("파일 업로드 성공.....");
			
			//썸네일 생성
			String thumbNailSaveName = "s_" + newName;
			board.setThumbnailName(thumbNailSaveName);
			
			File thumbfile = new File(uploadPath + thumbNailSaveName);
			File ufile = new File(uploadPath + newName);
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumbfile);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//db에 board(엔티티)를 저장
		Board b = boardRepo.save(board);
		
		if(b != null) {
			return "등록 성공...";
		}else {
			return "등록 실패...";
		}
		
	}
	
	//게시물 조회
	//@GetMapping("/getBoardList")
	public String getBoardList(Model model) {
		List<Board> list = boardRepo.findAll(); 
		
		model.addAttribute("boardList", list);
		return "member/boardList";
	}
	
	//@GetMapping("/detail")
	public String detail(@RequestParam("bno") int bno, Model model) {
		
		Optional <Board> result = boardRepo.findById(bno);
		Board board = result.get();
		
		model.addAttribute("board", board);
		return "/member/getBoard";
	}
}
