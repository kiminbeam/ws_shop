package com.exrestapi2.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exrestapi2.demo.dto.MemoDto;
import com.exrestapi2.demo.entity.Memo;
import com.exrestapi2.demo.repository.MemoRepository;

@RestController
@RequestMapping("/memoapp")
public class MemoController {
	
	@Autowired
	private MemoRepository memoRepo;
	
	/*
	@RequestMapping("/main")
	public String root() {
		return "hello memo";
	}
	*/
	
	//Get 메소드 (서버에 자료 요청할때 -어떤 자료인지 식별할 수 있는 파라미터가 있겠다.)
	//http://localhost:8093/memoapp/memo?mno=1
	@GetMapping("/memo")
	public Memo getMemo(@RequestParam("mno") Integer mno) {
		System.out.println("getMemo............ mno : " + mno);
		
		Optional <Memo> result= memoRepo.findById(mno);
		Memo memo = result.get();
		
		return memo;
	}
	
	//Post 메서드 (서버에 자료 새로 등록할 때 - ajax로 json 데이터가 파라미터로 넘어온다.)
	//http://localhost:8093/memoapp/memo
	//{ "title" : "test title..", "content" : "test content..", "writer" : "hgd"}
	@PostMapping("/memo")
	public Memo postMemo(@RequestBody MemoDto memoDto) {
		System.out.println("postMemo............. memoDto : " + memoDto);
		
		Memo memo = new Memo();
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepo.save(memo);
		
		return result;
	}
	
	//PUT 메서드(서버에 자료 재 등록할 때)
	//http://localhost:8093/memoapp/memo
	//{ "title" : "test title..", "content" : "test content..", "writer" : "hgd"}
	@PutMapping("/memo")
	public String putMemo(@RequestBody MemoDto memoDto) {
		System.out.println("putMemo............. memoDto : " + memoDto);
		
		Memo memo = new Memo();
		memo.setMno(memoDto.getMno());
		memo.setTitle(memoDto.getTitle());
		memo.setContent(memoDto.getContent());
		memo.setWriter(memoDto.getWriter());
		
		Memo result = memoRepo.save(memo);
		
		return result.toString();
	}
	
	//DELETE 메서드 (서버에 자료 삭제할 때 - 삭제할 자료를 식별할 수 있는 파라미터가 있어야겠다.)
	//http://localhost:8093/memoapp/memo/1
	@DeleteMapping("/memo/{mno}")
	public String deleteMemo(@PathVariable("mno") Integer mno) {
		System.out.println("deleteMemo............ mno : " + mno);
		
		memoRepo.deleteById(mno);
		
		return "deleteMemo............ mno : " + mno;
	}
}
