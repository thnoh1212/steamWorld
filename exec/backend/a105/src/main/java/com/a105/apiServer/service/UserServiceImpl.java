package com.a105.apiServer.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.a105.apiServer.dao.UserDao;
import com.a105.apiServer.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{

	UserDao userDao;
	
	PasswordEncoder passwordEncoder;
	
	
	@Autowired
	public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
		// TODO Auto-generated constructor stub
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}
	
	private String matchPassword(String userid, String password) throws Exception {

		UserDto user = userDao.getUser(userid);
//		System.out.println(user.getPassword());
//		System.out.println(passwordEncoder.matches(password, user.getPassword()));
		if(passwordEncoder.matches(password, user.getPassword())) {
			
			return  user.getPassword();
		}
		else {
			return null;
		}
	}
	
	@Override
	public UserDto login(Map map) throws Exception {
		String securePw = matchPassword((String) map.get("userid"),(String) map.get("password"));
		if(securePw != null) {
			map.put("password", securePw);
			return userDao.login(map);
		}
		else {
			return null;
		}
	}

	@Override
	public int join(UserDto user) throws Exception {
		// TODO Auto-generated method stub
		String securePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(securePassword);
		return userDao.insertUser(user);
	}

	@Override
	public UserDto info(String userid) throws Exception {
		// TODO Auto-generated method stub
//		System.out.println("userid : " + userid);

		return userDao.getUser(userid);
	}

	@Override
	public int modify(UserDto user) throws Exception {
		
		return userDao.updateUser(user);
		
	}	
	
	@Override
	public int modifyPassword(Map map) throws Exception {
		
			String secureNewPw = passwordEncoder.encode((String) map.get("newpassword"));
			map.put("newpassword", secureNewPw);
			return userDao.updatePassword(map);
		
	}

	@Override
	public int secede(Map map) throws Exception {
		String securePw = matchPassword((String) map.get("userid"),(String) map.get("password"));
		if(securePw != null) {
			map.put("password", securePw);
			return userDao.deleteUser(map);
		}
		else {
			return -1;
		}
	}

	@Override
	public int checkid(String userid) throws Exception {
		if(userDao.checkid(userid) == null) {
			return 1;
		}
		else{
			return 0;
		}
	}

	@Override
	public int addSteamid(Map map) throws Exception {
		// TODO Auto-generated method stub
		return userDao.addSteamid(map);
	}

}
