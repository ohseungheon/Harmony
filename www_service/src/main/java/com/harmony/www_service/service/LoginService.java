package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.LoginDao;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.dto.UserMemberDto;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	// 로그인 체크
	public UserDto loginCheckService(UserDto userDto) {
		
		return loginDao.loginCheck(userDto);
		
	}
	
	// 회원가입 user
	public int registUserService(UserMemberDto userMemberDto) {
		
		int resultUser = loginDao.registUser(userMemberDto);
		
		return resultUser;
	}
	
	// 회원가입 member
	public int registMemberService(UserMemberDto userMemberDto) {
		
		int resultMember = loginDao.registMember(userMemberDto);
		
		return resultMember;
		
	}

}
