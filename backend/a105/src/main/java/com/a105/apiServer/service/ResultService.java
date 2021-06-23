package com.a105.apiServer.service;

import java.util.List;
import java.util.Map;

import com.a105.apiServer.dto.ResultDto;

public interface ResultService {
	public List<ResultDto> resultList(Map map) throws Exception;
	public ResultDto resultSearch(Map map) throws Exception;
	public int resultAdd(List<ResultDto> list) throws Exception;
	public int resultDelete(String userid) throws Exception;
}
