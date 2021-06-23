package com.a105.apiServer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.a105.apiServer.dao.GameDao;
import com.a105.apiServer.dto.GameInfoDto;
import com.a105.apiServer.dto.GameLinksDto;
import com.a105.apiServer.dto.GameTagsDto;
import com.a105.apiServer.dto.GamesDto;
import com.a105.apiServer.dto.SizeDto;

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	GameDao gameDao;
	
	@Override
	public List<GamesDto> gameList(SizeDto size) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameList(size);
	}

	@Override
	public GamesDto gameSearch(String appid) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGame(appid);
	}


	@Override
	public List<GamesDto> searchName(String word) throws Exception{
		return gameDao.searchName(word);
	}
	
	@Override
	public List<GameTagsDto> gameTagAll(SizeDto size) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameTagAll(size);
	}

	@Override
	public List<GameTagsDto> gameTagList(String appid) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameTagList(appid);
	}

	@Override
	public GameTagsDto gameTagSearch(String gametagid) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameTag(gametagid);
	}

	@Override
	public List<GameLinksDto> gameLinkAll(SizeDto size) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameLinkAll(size);
	}

	@Override
	public List<GameLinksDto> gameLinkList(String appid) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameLinkList(appid);
	}

	@Override
	public GameLinksDto gameLinkSearch(String linkid) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGameLink(linkid);
	}

	@Override
	public List<GameInfoDto> gameInfoList() throws Exception {
		List<GamesDto> games = gameDao.getGameList(new SizeDto(0, 1000));
		List<GameTagsDto> tags = gameDao.getGameTagAll(new SizeDto(0, 10000));
		List<GameLinksDto> links = gameDao.getGameLinkAll(new SizeDto(0, 10000));
		
		List<GameInfoDto> infos = new ArrayList<GameInfoDto>();
		for(GamesDto g : games) {
			int appid = g.getAppid();			
			
			GameInfoDto info = new GameInfoDto();
			info.setAppid(appid);
			info.setGame(g);
			
			List<GameTagsDto> gts = new ArrayList<GameTagsDto>();
			List<GameLinksDto> gls = new ArrayList<GameLinksDto>();
			
			for(GameTagsDto gt : tags) {
				if(gt.getAppid() == appid) {
					gts.add(gt);
				}
			}
			
			for(GameLinksDto gl : links) {
				if(gl.getAppid() == appid) {
					gls.add(gl);
				}
			}
			info.setGametags(gts);
			info.setGamelinks(gls);
			infos.add(info);
		}
		return infos;
	}

	@Override
	public GameInfoDto gameInfoSearch(String appid) throws Exception {
		GamesDto g = gameDao.getGame(appid);
		int aid = g.getAppid();
		GameInfoDto info = new GameInfoDto();
		info.setAppid(aid);
		info.setGame(g);
		
		List<GameTagsDto> tags = gameDao.getGameTagAll(new SizeDto(0, 200000));
		List<GameLinksDto> links = gameDao.getGameLinkAll(new SizeDto(0, 300000));
		
		List<GameTagsDto> gts = new ArrayList<GameTagsDto>();
		List<GameLinksDto> gls = new ArrayList<GameLinksDto>();
		
		for(GameTagsDto gt : tags) {
			if(gt.getAppid() == aid) {
				gts.add(gt);
			}
		}
		
		for(GameLinksDto gl : links) {
			if(gl.getAppid() == aid) {
				gls.add(gl);
			}
		}
		
		info.setGametags(gts);
		info.setGamelinks(gls);
		return info;
	}

	@Override
	public List<GamesDto> popularList(SizeDto size) throws Exception {
		// TODO Auto-generated method stub
		return gameDao.getGamePopular(size);
	}

}
