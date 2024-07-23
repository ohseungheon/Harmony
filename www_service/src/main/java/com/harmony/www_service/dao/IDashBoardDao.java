package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.MemberDtoForDashBoard;

@Mapper
public interface IDashBoardDao {
	// for 연령대별 회원 수 통계
	public List<MemberDtoForDashBoard> findMemByAges();
}
