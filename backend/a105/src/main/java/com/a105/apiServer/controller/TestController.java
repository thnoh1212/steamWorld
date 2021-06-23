package com.a105.apiServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.a105.apiServer.dto.TestDto;
import com.a105.apiServer.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@GetMapping(value = "")
	private ResponseEntity test() {
		ResponseEntity entity = null;
		Map result = new HashMap();
		result.put("success", "test success");
		entity = new ResponseEntity(result, HttpStatus.OK);
		return entity;
	}
	
	@GetMapping(value = "all")
	private ResponseEntity testAll() {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<TestDto> dto = testService.findAll();
			System.out.println(dto);
			if(dto != null) {
				result.put("success", "success");
				result.put("data", dto);				
			}
			else {
				result.put("success", "fail");
			}
			entity = new ResponseEntity(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PostMapping(value = "create")
	private ResponseEntity createTest(@RequestBody TestDto test) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			int succ = testService.createTest(test);
			if(succ == 1) {
				result.put("success", "success");	
			}
			else {
				result.put("success", "fail");
			}
			entity = new ResponseEntity(result, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
