package com.study.springboot.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
	@Autowired
	Member member1;
	@Autowired
	@Qualifier("printerB")
	Printer printer;
	@Autowired
	Member member2;
	
	@RequestMapping("/hello")
	public @ResponseBody String root() {
		
		// Member 빈 가져오기
		member1.print();
		
		// PrinterB 빈 가져오기
		member1.setPrinter(printer);
		member1.print();
		
		// 싱글톤 비교
		if(member1 == member2) {
			System.out.println("동일한 객체입니다.");
		}
		else {
			System.out.println("서로 다른 객체입니다.");
		}
		return "Annotation 사용하기";
	}
}
