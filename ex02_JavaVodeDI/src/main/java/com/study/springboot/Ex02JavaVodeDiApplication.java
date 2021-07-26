package com.study.springboot;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.springboot.bean.Config;
import com.study.springboot.bean.Member;
import com.study.springboot.bean.Printer;

//@SpringBootApplication
public class Ex02JavaVodeDiApplication {

	public static void main(String[] args) {
		//SpringApplication.run(Ex02JavaVodeDiApplication.class, args);
		
		// IoC 컨테이너 생성
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		
		// Member Bean 가져오기
		Member member1 = (Member)context.getBean("member1");
		member1.print();
		
		// Member Bean 가져오기
		Member member2 = context.getBean("hello", Member.class);
		member2.print();
		
		// Member Bean 가져오기
		Member member3 = (Member)context.getBean("hello");
		member3.print();
		
		// PrinterB Bean 가져오기
		Printer printer = context.getBean("printerB", Printer.class);
		member1.setPrinter(printer);  // 여기서 printerA에서 printerB로 바뀜
		member1.print();
		
		// member1, member2 비교
		System.out.print("member1과 member2는 ");
		if (member1 == member2) {
			System.out.println("동일한 객체입니다.");
		}
		else {
			System.out.println("다른 객체입니다.");
		}
		// member2, member3 비교
		System.out.print("member2와 member3은 ");
		if (member2 == member3) {
			System.out.println("동일한 객체입니다.");
		}
		else {
			System.out.println("다른 객체입니다.");
		}
	}

}
