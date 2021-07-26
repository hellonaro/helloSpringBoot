package com.study.springboot.jdbc;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper // 아래 인터페이스 구현을 XML로 작성
public interface IMyUserDao {
	List<MyUserDTO> list();
}
