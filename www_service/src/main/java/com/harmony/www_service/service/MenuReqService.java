package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IRequestMenuDao;
import com.harmony.www_service.dto.MenuReqDto;

@Service
public class MenuReqService {

	@Autowired
	IRequestMenuDao rmDao;
	
	// 메뉴 추천 리스트 불러오기
	public List<MenuReqDto> getReqMenuList(int mrcode){
		List<MenuReqDto> list = rmDao.findMenuRequest(mrcode);
		return list;
	}
	// 승인 - 메뉴 등록하기
}
