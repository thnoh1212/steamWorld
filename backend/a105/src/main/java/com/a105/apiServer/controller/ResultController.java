package com.a105.apiServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a105.apiServer.dto.ItemDto;
import com.a105.apiServer.dto.ResultDto;
import com.a105.apiServer.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {
	
	@Autowired
	ResultService resultService;
	
	@GetMapping(value = "/list")
	private ResponseEntity resultList(@RequestParam String userid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		Map map = new HashMap();
		map.put("userid", userid);
		try {
			List<ResultDto> list = resultService.resultList(map);
			if (list != null) {
				result.put("success", "success");
				result.put("data", list);
				entity = new ResponseEntity<>(result, HttpStatus.OK);
				
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
	
	@GetMapping(value = "/search")
	private ResponseEntity resultSearch(@RequestParam String userid, @RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		Map map = new HashMap();
		map.put("userid", userid);
		map.put("appid", appid);
		try {
			ResultDto resultDto = resultService.resultSearch(map);
			if (resultDto != null) {
				result.put("success", "success");
				result.put("data", resultDto);
				entity = new ResponseEntity<>(result, HttpStatus.OK);
				
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
			
		}
		return entity;
	}
	

	@PostMapping(value="/add")
	private ResponseEntity addResult(@RequestBody List<ResultDto> list) {
		Map result = new HashMap();
		ResponseEntity entity = null;
		
		try {
			if (resultService.resultAdd(list) == 1) {
				result.put("success", "success");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
				
			}
			else {
				result.put("success", "fail");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error"); 
			entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@DeleteMapping(value="/delete")
	private ResponseEntity addResult(@RequestHeader String userid) {
		Map result = new HashMap();
		ResponseEntity entity = null;

//		userid = userid.substring(0, userid.length()-1);

//		userid= userid.replace("%40", "@");	
//		System.out.println("@@@@@@@@" +userid);
		try {
			if (resultService.resultDelete(userid) >=1){
				result.put("success", "success");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
				
			}
			else {
//				System.out.println("####");
				result.put("success", "fail");
				entity = new ResponseEntity<>(result, HttpStatus.OK);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", "error"); 
			entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}
