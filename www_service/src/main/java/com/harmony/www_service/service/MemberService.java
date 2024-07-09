package com.harmony.www_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IMemberDao;
import com.harmony.www_service.dto.MemberDto_by;

@Service
public class MemberService {
	@Autowired
	IMemberDao memDao;
	
	//회원정보수정
	public void updateMemberInfo(MemberDto_by memberDto) {
		memDao.updateMemberInfo1(memberDto);
		memDao.updateMemberInfo2(memberDto);
	}
	//회원번호를 가져오는 서비스 
	public MemberDto_by getMemberByUsername(String username) {
		return memDao.getmemberList(username);
	}

	public Optional<Integer> getMnoByUsername(String username){
		Optional<Integer> mno = memDao.getMemberNoByUsername(username);
		return mno;
	}
}
