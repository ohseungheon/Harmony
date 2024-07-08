package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MemberDto;

@Mapper
public interface IMemberDao {
	//로그인한 사용자의 번호를 받아 회원정보 가져와서 정보수정 
	public MemberDto getmemberList(@Param("mno") int mno);
}
