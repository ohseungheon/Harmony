package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.UserDto;
import com.harmony.www_service.dto.UserMemberDto;

@Mapper
public interface LoginDao {
	
	//유저아이디로 정보 가져오기
	public UserDto findByUsername(@Param("username") String username);

	//로그인
	public UserDto loginCheck(UserDto userDto);
	
	//username 으로 mno 가져오기
	public MemberDto getMnoByUsername(@Param("username") String username);
	
	//회원가입 user
	public int registUser(UserMemberDto userMemberDto);
	
	//회원가입 member
	public int registMember(UserMemberDto userMemberDto);
	
	//nickname 가져오기
	public String getNickname(@Param("username") String username);
	
	//아이디 중복검사
	public int checkUsername(String username);
	
}