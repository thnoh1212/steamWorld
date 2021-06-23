package com.a105.apiServer.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.a105.apiServer.service.EmailService;
import com.a105.apiServer.service.EmailServiceImpl;



@Controller
@RequestMapping("/mail/*")
public class MailController {
	@Autowired
	EmailService service;

	private static final Logger logger = LoggerFactory.getLogger(MailController.class);

	@PostMapping("")
	@ResponseBody
	public ResponseEntity emailConfirm(@RequestBody String userId){
		
		ResponseEntity entity = null;
		Map result = new HashMap();
		
		logger.info("post emailConfirm");
		
		userId = userId.substring(0, userId.length()-1);
		userId= userId.replace("%40", "@");	
		userId= userId.replace("%22", "\"");
		
		logger.info("email : "+ userId);
		try {
			service.sendSimpleMessage(userId);
//			service.testKey();
			result.put("success", "success");
			entity = new ResponseEntity(result, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("success", "error"); 
	        entity = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		return entity;
	}

	@PostMapping("/verify")
	@ResponseBody
	public ResponseEntity verifyCode(@RequestBody String code) {
		logger.info("Post verifyCode");

		ResponseEntity entity = null;
		Map result = new HashMap();
		code = code.substring(0, code.length()-1);
		System.out.println(code);
//		int result = 0;
//		System.out.println("code : " + code);
//		System.out.println("ePw : " + EmailServiceImpl.ePw);
//		System.out.println("code match : " + EmailServiceImpl.ePw.equals(code));
		if (EmailServiceImpl.ePw.equals(code)) {
			result.put("success", "success");
			entity = new ResponseEntity(result, HttpStatus.OK);
		}else {
        	result.put("success", "fail");
        	entity = new ResponseEntity(result, HttpStatus.OK);
		}

		return entity;
	}
	
}