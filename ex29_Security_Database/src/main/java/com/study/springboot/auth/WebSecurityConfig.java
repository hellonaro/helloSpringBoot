package com.study.springboot.auth;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public AuthenticationFailureHandler authenticationFailureHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
			.antMatchers("/guest/**").permitAll()
			.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().authenticated();
		
		http.formLogin()
			.loginPage("/loginForm") // default: /login
			.loginProcessingUrl("/j_spring_security_check")  // 스프링 시큐리티 인증 url
			//.failureUrl("/loginForm?error")  // default: /login?error
			.failureHandler(authenticationFailureHandler)
			.usernameParameter("j_username")  // jsp에서 지정한 변수명
			.passwordParameter("j_password")  // jsp에서 지정한 변수명
			.permitAll();
		
		http.logout()
			.logoutUrl("/logout") // default
			.logoutSuccessUrl("/")
			.permitAll();
		
		http.csrf().disable();
	}
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication()
//			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
//			.and()
//			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
//	}
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws
	Exception{
		System.out.println(passwordEncoder().encode("123"));  // 테스트용 코드
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select name as userName, password, enabled from user_list where name = ?")  // 해당 사용자 있는지 조회
			.authoritiesByUsernameQuery("select name as userName, authority from user_list where name = ?")  // 해당 사용자의 역할 조회
			.passwordEncoder(new BCryptPasswordEncoder());  // 입력한 비밀번호 암호화 후 데이터베이스 암호와 비교
		
		/*
		 * 스프링 시큐리티가 사용자 인증에 사용하는 컬럼 이름은 username과 password이나 생성한 테이블의 컬럼이름은 name, password이다.
		 * 쿼리문에서 'name as username' 처럼 별칭을 사용하면 테이블의 컬럼명을 변경하지 않아도 된다.
		 */
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
