package com.a105.apiServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.a105.apiServer.dto.GameInfoDto;
import com.a105.apiServer.dto.GameLinksDto;
import com.a105.apiServer.dto.GameTagsDto;
import com.a105.apiServer.dto.GamesDto;
import com.a105.apiServer.dto.SizeDto;
import com.a105.apiServer.dto.UserDto;
import com.a105.apiServer.service.GameService;

@RestController
@RequestMapping("/game")
public class GameController {
	
	@Autowired
	GameService gameService;
	
	@GetMapping(value = "/list")
	private ResponseEntity gameList(@RequestParam int start, @RequestParam int size) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GamesDto> games = gameService.gameList(new SizeDto(start, size));
			if (games != null) {
				result.put("success", "success");
				result.put("data", games);
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
	
	@GetMapping(value = "/popular")
	private ResponseEntity popularList(@RequestParam int start, @RequestParam int size) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GamesDto> games = gameService.popularList(new SizeDto(start, size));
			if (games != null) {
				result.put("success", "success");
				result.put("data", games);
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
	private ResponseEntity gameSearch(@RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			GamesDto game = gameService.gameSearch(appid);
			if (game != null) {
				result.put("success", "success");
				result.put("data", game);
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
	
	@GetMapping(value = "/search/name")
	private ResponseEntity nameSearch(@RequestParam String word) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GamesDto> game = gameService.searchName(word);
			if (game != null) {
				result.put("success", "success");
				result.put("data", game);
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
	
	@GetMapping(value = "/info/list")
	private ResponseEntity gameInfoList() {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GameInfoDto> infos = gameService.gameInfoList();
			if (infos != null) {
				result.put("success", "success");
				result.put("data", infos);
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
	
	@GetMapping(value = "/info/search")
	private ResponseEntity gameInfoSearch(@RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			GameInfoDto infos = gameService.gameInfoSearch(appid);
			if (infos != null) {
				result.put("success", "success");
				result.put("data", infos);
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
	
	@GetMapping(value = "/tag/all")
	private ResponseEntity gameTagAll(@RequestParam int start, @RequestParam int size) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GameTagsDto> tags = gameService.gameTagAll(new SizeDto(start, size));
			if (tags != null) {
				result.put("success", "success");
				result.put("data", tags);
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
	
	@GetMapping(value = "/tag/list")
	private ResponseEntity gameTagList(@RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GameTagsDto> tags = gameService.gameTagList(appid);
			if (tags != null) {
				result.put("success", "success");
				result.put("data", tags);
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
	
	@GetMapping(value = "/tag/search")
	private ResponseEntity gameTagSearch(@RequestParam String gametagid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			GameTagsDto tags = gameService.gameTagSearch(gametagid);
			if (tags != null) {
				result.put("success", "success");
				result.put("data", tags);
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
	
	@GetMapping(value = "/link/all")
	private ResponseEntity gameLinkAll(@RequestParam int start, @RequestParam int size) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GameLinksDto> links = gameService.gameLinkAll(new SizeDto(start, size));
			if (links != null) {
				result.put("success", "success");
				result.put("data", links);
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
	
	@GetMapping(value = "/link/list")
	private ResponseEntity gameLinkList(@RequestParam String appid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			List<GameLinksDto> links = gameService.gameLinkList(appid);
			if (links != null) {
				result.put("success", "success");
				result.put("data", links);
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
	
	@GetMapping(value = "/link/search")
	private ResponseEntity gameLinkSearch(@RequestParam String linkid) {
		ResponseEntity entity = null;
		Map result = new HashMap();
		try {
			GameLinksDto links = gameService.gameLinkSearch(linkid);
			if (links != null) {
				result.put("success", "success");
				result.put("data", links);
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
