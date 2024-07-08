package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.LoginDao;
import com.harmony.www_service.dto.UserDto;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	// 로그인 체크
	public UserDto loginCheckService(UserDto userDto) {
		
		return loginDao.loginCheck(userDto);
		
	}

}
