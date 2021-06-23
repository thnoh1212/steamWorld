package com.a105.apiServer.service;

import java.util.List;
import java.util.Map;

import com.a105.apiServer.dto.ItemDto;
import com.a105.apiServer.dto.UserItemDto;

public interface ItemService {
	public List<ItemDto> itemList(String userid, String isSteam) throws Exception;
	public ItemDto itemSearch(String userid, String appid) throws Exception;
	public int itemAdd(List<ItemDto> list) throws Exception;
	public int itemDelete(Map map) throws Exception;
	public List<UserItemDto> formSearch(Map map) throws Exception;

}
