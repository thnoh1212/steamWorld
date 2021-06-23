package com.a105.apiServer.service;

import java.util.Map;

import com.a105.apiServer.dto.UserDto;

public interface UserService {

	public UserDto login(Map map) throws Exception;
	public int join(UserDto user) throws Exception;
	public UserDto info(String userid) throws Exception;
	public int modify(UserDto user) throws Exception;
	public int modifyPassword(Map map) throws Exception;
	public int secede(Map map) throws Exception;
	public int checkid(String userid) throws Exception;
	public int addSteamid(Map map) throws Exception;

}
