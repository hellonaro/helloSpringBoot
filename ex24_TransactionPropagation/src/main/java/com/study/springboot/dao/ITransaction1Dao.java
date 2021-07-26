package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ITransaction1Dao {
	public void pay(@Param("_consumerId") String consumerId, @Param("_amount") int amount);
}
