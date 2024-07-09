package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.dto.UserMemberDto;

@Mapper
public interface LoginDao {

	//로그인
	public UserDto loginCheck(UserDto userDto);
	
	//회원가입 user
	public int registUser(UserMemberDto userMemberDto);
	
	//회원가입 member
	public int registMember(UserMemberDto userMemberDto);
	
}