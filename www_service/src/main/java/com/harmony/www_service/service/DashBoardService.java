package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IDashBoardDao;
import com.harmony.www_service.dto.GenderDtoForDashBoard;
import com.harmony.www_service.dto.MemberDtoForDashBoard;

@Service
public class DashBoardService {

	@Autowired
	IDashBoardDao dbDao;
	
	// 총 회원수
	public int findAll(){
		return dbDao.findAll();
	}
	
	// 새 회원수 (일별)
	public int findNewDayMember(){
		return dbDao.findNewDayMember();
	}
	
	// 새 회원수 (월별)
	public int findNewMonthMember(){
		return dbDao.findNewMonthMember();
	}
	
	// 회원 성비 구하기
	public GenderDtoForDashBoard findMemByGender(){
		return dbDao.findMemByGender();
	}
	
	// 회원 나이
	public int[] findMemByAges() {
		return dbDao.findMemByAges();
	}
	
	//회원 정보 + 회원이 등록한 레시피 수
	public List<MemberDtoForDashBoard> findMemberInfo(){
		return dbDao.findMemAllInfo();
	}
}