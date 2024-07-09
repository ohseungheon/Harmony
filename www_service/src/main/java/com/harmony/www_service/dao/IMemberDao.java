package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.MemberDto_by;

@Mapper
public interface IMemberDao {
	//로그인한 사용자의 username를 받아 회원정보 출력 
	public MemberDto_by getmemberList(String username);
	
	//회원번호로 회원정보 수정 
	public void updateMemberInfo1(@Param("member")MemberDto_by member);
	// 비밀번호 수정
	public void updateMemberInfo2(@Param("member")MemberDto_by member);
}
