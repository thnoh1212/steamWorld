package com.a105.apiServer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.a105.apiServer.dto.ResultDto;

@Mapper
public interface ResultDao {
	public List<ResultDto> getResultList(Map map) throws Exception;
	public ResultDto getResult(Map map) throws Exception;
	public int insertResult(ResultDto resultDto) throws Exception;
	public int deleteResult(String userid) throws Exception;
}
