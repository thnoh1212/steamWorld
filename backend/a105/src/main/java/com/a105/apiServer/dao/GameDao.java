package com.a105.apiServer.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.a105.apiServer.dto.GameLinksDto;
import com.a105.apiServer.dto.GameTagsDto;
import com.a105.apiServer.dto.GamesDto;
import com.a105.apiServer.dto.SizeDto;

@Mapper
public interface GameDao {
	public List<GamesDto> getGameList(SizeDto size) throws Exception;
	public List<GamesDto> getGamePopular(SizeDto size) throws Exception;
	public GamesDto getGame(String appid) throws Exception;
	public List<GamesDto> searchName(String word) throws Exception;
	
	public List<GameTagsDto> getGameTagAll(SizeDto size) throws Exception;
	public List<GameTagsDto> getGameTagList(String appid) throws Exception;
	public GameTagsDto getGameTag(String gametagid) throws Exception;
	
	public List<GameLinksDto> getGameLinkAll(SizeDto size) throws Exception;
	public List<GameLinksDto> getGameLinkList(String appid) throws Exception;
	public GameLinksDto getGameLink(String linkid) throws Exception;
	
	
}
