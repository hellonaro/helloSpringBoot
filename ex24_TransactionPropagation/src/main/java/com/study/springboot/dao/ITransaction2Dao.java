package com.study.springboot.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ITransaction2Dao {
	public void pay(@Param("_consumerId") String consumerId, @Param("_amount") int amount);
}
