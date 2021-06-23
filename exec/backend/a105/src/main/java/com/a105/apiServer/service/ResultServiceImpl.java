package com.a105.apiServer.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a105.apiServer.dao.ResultDao;
import com.a105.apiServer.dto.ResultDto;

@Service
public class ResultServiceImpl implements ResultService{

	@Autowired
	ResultDao resultDao;
	
	@Override
	public List<ResultDto> resultList(Map map) throws Exception {
		// TODO Auto-generated method stub
		return resultDao.getResultList(map);
	}

	@Override
	public ResultDto resultSearch(Map map) throws Exception {
		// TODO Auto-generated method stub
		return resultDao.getResult(map);
	}

	@Override
	public int resultAdd(List<ResultDto> list) throws Exception {
		// TODO Auto-generated method stub
				int flag = 1;
				if(list == null) {
					return 2;
				}
				for(ResultDto item : list) {
					flag = resultDao.insertResult(item);
					if(flag == 0) {
						break;
					}
				}
				
				return flag;
	}
	
	public int resultDelete(String userid) throws Exception{
//		System.out.println("impl userid : " + userid);
//		System.out.println(resultDao.deleteResult(userid));
		return resultDao.deleteResult(userid);
	}

}
