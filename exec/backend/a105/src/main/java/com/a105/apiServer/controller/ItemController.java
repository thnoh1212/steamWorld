package com.a105.apiServer.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.a105.apiServer.dto.UserDto;
import com.a105.apiServer.dto.UserItemDto;
import com.a105.apiServer.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	ItemService itemService;
	
	@GetMapping(value = "list")
	private ResponseEntity itemList(@RequestParam String userid, @RequestParam String issteam) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<ItemDto> list = itemService.itemList(userid, issteam);
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
	
	@GetMapping(value = "search")
	private ResponseEntity itemSearch(@RequestParam String userid, @RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			ItemDto itemDto = itemService.itemSearch(userid, appid);
			if (itemDto != null) {
				result.put("success", "success");
				result.put("data", itemDto);
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
	private ResponseEntity addItem(@RequestBody List<ItemDto> list) {
		Map result = new HashMap();
		ResponseEntity entity = null;
		
		try {
			if (itemService.itemAdd(list) == 1) {
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
	private ResponseEntity removeItem(@RequestHeader Map map) {
		Map result = new HashMap();
		ResponseEntity entity = null;
		
		try {
			if (itemService.itemDelete(map) == 1) {
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
	
	@GetMapping(value="/form")
	private ResponseEntity formSearch(@RequestParam Map map) {
		Map result = new HashMap();
		ResponseEntity entity = null;
		
		try {
			List<UserItemDto> list = itemService.formSearch(map);
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
	
//	@GetMapping(value="/test")
//	private void test(@RequestParam Map map) throws IOException {
//		String url = "https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key=90F5B0B48046ABC1FAB7888D79EF4D6A&steamid="
//				+ map.get("steamid");
//		StringBuilder urlBuilder = new StringBuilder(url);
//		URL pURL = new URL(urlBuilder.toString());
//		HttpURLConnection conn = (HttpURLConnection) pURL.openConnection();
//		conn.setRequestMethod("GET");
//		System.out.println(conn.getResponseCode());
//		BufferedReader rd;
//		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		StringBuilder sb = new StringBuilder();
//		String line;
//		while((line = rd.readLine()) != null) {
//			sb.append(line);
//		}
//		rd.close();
//		conn.disconnect();
//		System.out.println(sb.toString());
//	}
	
}
