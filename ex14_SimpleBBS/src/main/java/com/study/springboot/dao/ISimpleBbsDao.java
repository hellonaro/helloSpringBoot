package com.study.springboot.dao;

import java.util.List;

import com.study.springboot.dto.SimpleBbsDto;

public interface ISimpleBbsDao {
	public List<SimpleBbsDto> listDao();  // 리스트를 보기 위한 select 메소드 정의
	public SimpleBbsDto viewDao(String id); // 개별 뷰 보기를 위한 select 메소드 정의
	public int writeDao(String writer, String title, String content); // 글 작성 위한 insert 메소드 정의
	public int deleteDao(String id);  // 글 삭제를 위한 delete 메소드 정의
}