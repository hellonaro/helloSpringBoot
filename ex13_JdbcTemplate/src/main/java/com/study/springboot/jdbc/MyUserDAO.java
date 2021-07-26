package com.study.springboot.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository // 데이터베이스 관련 처리 용도로 빈을 등록
public class MyUserDAO {
	@Autowired // 자동 주입 : 설정한 데이터베이스의 정보를 바탕으로 데이터베이스 접속 후 데이터가 들어옴
	private JdbcTemplate jdbcTemplate;
	
	public List<MyUserDTO> list(){
		String query = "select * from myuser";
		List<MyUserDTO> list = jdbcTemplate.query(
				query, new BeanPropertyRowMapper<MyUserDTO>(MyUserDTO.class));
		
		for(MyUserDTO user : list) {
			System.out.println(user);
		}
		
		return list;
	}
}