package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.LoginDao;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.UserMemberDto;

@Service
public class LoginService {
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	
	// 회원가입 user
	public int registUserService(UserMemberDto userMemberDto) {
		
		userMemberDto.setRole("ROLE_USER");
		userMemberDto.setPassword(pwEncoder.encode(userMemberDto.getPassword()));
		int resultUser = loginDao.registUser(userMemberDto);
		
		return resultUser;
	}
	
	// 회원가입 member
	public int registMemberService(UserMemberDto userMemberDto) {
		
		int resultMember = loginDao.registMember(userMemberDto);
		
		return resultMember;
		
	}
	
	//username으로 mno 가져오기
	public MemberDto getMnoByUsernameService(String username) {
		
		MemberDto result = loginDao.getMnoByUsername(username);
		
		return result;
		
	}

}
