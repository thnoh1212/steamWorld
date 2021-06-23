package com.a105.apiServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a105.apiServer.dao.TestDao;
import com.a105.apiServer.dto.TestDto;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;

	@Override
	public List<TestDto> findAll() throws Exception {
		// TODO Auto-generated method stub
		return testDao.searchAll();
	}

	@Override
	public int createTest(TestDto test) throws Exception {
		// TODO Auto-generated method stub
		return testDao.insertTest(test);
	}
	
}
