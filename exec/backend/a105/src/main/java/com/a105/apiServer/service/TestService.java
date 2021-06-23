package com.a105.apiServer.service;

import java.util.List;

import com.a105.apiServer.dto.TestDto;

public interface TestService {
	public List<TestDto> findAll() throws Exception;
	public int createTest(TestDto test) throws Exception;
}
