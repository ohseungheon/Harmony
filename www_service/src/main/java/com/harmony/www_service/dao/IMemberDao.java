package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MemberDto;

@Mapper
public interface IMemberDao {
	//로그인한 사용자의 번호를 받아 회원정보 출력 
	public MemberDto getmemberList(@Param("mno") int mno);
	
	//회원번호로 회원정보 수정 
	public void updateMemberInfo1(@Param("member")MemberDto member);
	// 비밀번호 수정
	public void updateMemberInfo2(@Param("member")MemberDto member);
}
