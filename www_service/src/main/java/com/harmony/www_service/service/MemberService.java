package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dto.MemberDto;

@Service
public class MemberService {
	@Autowired
	IMemberDao memDao;
	
	public void updateMemberInfo(MemberDto memberDto) {
		memDao.updateMemberInfo1(memberDto);
		memDao.updateMemberInfo2(memberDto);
	}
}
