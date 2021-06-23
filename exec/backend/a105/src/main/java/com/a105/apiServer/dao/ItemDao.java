package com.a105.apiServer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.a105.apiServer.dto.ItemDto;
import com.a105.apiServer.dto.UserItemDto;

@Mapper
public interface ItemDao {
	public List<ItemDto> getItemList(String userid, String issteam) throws Exception;
	public ItemDto getItem(String userid, String appid) throws Exception;
	public int addItem(ItemDto itemDto) throws Exception;
	public int deleteItem(Map map) throws Exception;
	public List<UserItemDto> getUserItem(Map map) throws Exception;
}
	
