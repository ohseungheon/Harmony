package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.dto.UserMemberDto;

@Mapper
public interface LoginDao {
	
	//유저아이디로 정보 가져오기
	public UserDto findByUsername(@Param("username") String username);

	//로그인
	public UserDto loginCheck(UserDto userDto);
	
	//로그인 체크
	public UserDto loginCheck(@Param("username") String username, @Param("password") String password);
	
	//회원가입 user
	public int registUser(UserMemberDto userMemberDto);
	
	//회원가입 member
	public int registMember(UserMemberDto userMemberDto);
	
	//nickname 가져오기
	public String getNickname(@Param("username") String username);
	
}