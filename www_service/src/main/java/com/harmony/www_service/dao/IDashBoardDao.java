package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.MemberDtoForDashBoard;

@Mapper
public interface IDashBoardDao {
	
	// 총 회원수
	public int findAll();
	
	// 새 회원수 (일별)
	public int findNewDayMember();
	
	// 새 회원수 (월별)
	public int findNewMonthMember();
	
	// 회원 성비 구하기
	public double findMemByGender();
	
	// 회원 나이 구하기 - for 연령대별 회원 수
	public int[] findMemByAges();
	
	//회원 정보 + 회원이 등록한 레시피 수
	public List<MemberDtoForDashBoard> findMemberInfo();
}
