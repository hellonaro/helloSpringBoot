package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Lombok 사용하기!";
	}
	
	@RequestMapping("/test")
	public String test(Member member, Model model) {
		// 파라미터와 일치하는 빈을 만들어 사용
		// view 페이지에 model이 아닌 member 사용
		return "test";
	}
}
