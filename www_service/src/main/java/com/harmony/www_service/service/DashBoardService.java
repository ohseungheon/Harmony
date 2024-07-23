package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IDashBoardDao;
import com.harmony.www_service.dto.MemberDtoForDashBoard;

@Service
public class DashBoardService {

	@Autowired
	IDashBoardDao dbDao;
	
	public List<MemberDtoForDashBoard> NumberOfMemsByAgeGroup() {
		return dbDao.findMemByAges();
	}
	
}