package com.a105.apiServer.dao;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.a105.apiServer.dto.TestDto;

@Mapper
public interface TestDao {
	public List<TestDto> searchAll() throws Exception;
	public int insertTest(TestDto Test) throws Exception;
}
