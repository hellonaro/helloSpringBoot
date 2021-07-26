package com.study.springboot.bean;

import org.springframework.context.annotation.*;

@Configuration
public class Config {
	// Bean : 스프링이  IoC 방식으로 관리하는 객체
	// BeanFactory : 스프링의 IoC를 담당하는 핵심 컨테이너
	// ApplicationContext : 빈팩토리를 확장한 IoC 컨테이너
	
	@Bean
	public Member member1() {
		// Setter 메소드를 이용한 의존성 주입
		Member member1 = new Member();
		member1.setName("강나래");
		member1.setNickname("나로");
		member1.setPrinter(new PrinterA());
		
		return member1;
	}
	
	@Bean(name="hello")
	public Member member2() {
		// 생성자를 이용한 의존성 주입
		return new Member("현은비", "앤", new PrinterA());
	}
	
	@Bean
	public PrinterA printerA() {
		return new PrinterA();
	}
	
	@Bean
	public PrinterB printerB() {
		return new PrinterB();
	}
}
