package com.exrestapi.demo.mycontroller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {
	
	//http://localhost:8093/api/v1/delete-api/123
	@DeleteMapping("/{variable}")
	public String deleteVariable(@PathVariable("variable") String variable) {
		return variable;
	}
	
	//http://localhost:8093/api/v1/delete-api/request1?email=aaa@green.com
	@DeleteMapping("/request1")
	public String deleteRequestParam(@RequestParam("email") String email) {
		return "email : " + email;
	}
}
