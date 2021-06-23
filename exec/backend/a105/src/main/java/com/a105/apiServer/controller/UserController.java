package com.a105.apiServer.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a105.apiServer.dto.UserDto;
import com.a105.apiServer.service.JwtService;
import com.a105.apiServer.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	//로그인
	@PostMapping(value = "/login")
	private ResponseEntity login(@RequestBody Map map) {
		Map result = new HashMap();
		ResponseEntity entity = null;
		try {
			UserDto user = userService.login(map);
			if(user != null) {
				String token = jwtService.create(user);
				logger.trace("token", token);
				result.put("success", "success");
				result.put("x-access-token", token);
				user.setPassword("");
				result.put("data", user);
			}
			else {
				result.put("success", "fail");
				
			}
			entity = new ResponseEntity(result, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("success", "error");
			entity = new ResponseEntity(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@PostMapping(value="/join")
	private ResponseEntity join(@RequestBody UserDto user) {
		Map result = new HashMap();
        ResponseEntity entity = null;
        
        try {
        	if (userService.join(user) == 1) {
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
	
	@GetMapping(value = "")
	private ResponseEntity profile(@RequestParam String userid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			userid = userid.replace("%40", "@");
			UserDto member = userService.info(userid);
			if (member != null) {
				result.put("success", "success");
				member.setPassword("");
				result.put("data", member);
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
	
	@PutMapping(value = "")
	private ResponseEntity update(@RequestBody UserDto user) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			if (userService.modify(user) == 1) {
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
	
	@PutMapping(value="/password")
	private ResponseEntity updatePw(@RequestBody Map map) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			if (userService.modifyPassword(map) == 1) {
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
	
	
	@DeleteMapping(value="")
	private ResponseEntity delete(@RequestHeader Map map) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			if (userService.secede(map)== 1) {
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
	
	@PostMapping(value="/checkid")
	private ResponseEntity idCheck(@RequestBody Map map) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			if (userService.checkid((String) map.get("userid")) == 1) {
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
	
	@PostMapping(value="/steam")
	private ResponseEntity plusSteamid(@RequestBody Map map) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			if (userService.addSteamid(map) == 1) {
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
}
