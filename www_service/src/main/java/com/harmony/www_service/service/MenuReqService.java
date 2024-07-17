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

	// 요청 메뉴 리스트 불러오기
	public List<MenuReqDto> getReqMenuList() {
		System.out.println("---------- menuReq service 진입 ----------");
		List<MenuReqDto> list = rmDao.findMenuRequest();

		System.out.println("================== list : " + list);
		return list;
	}

	// 승인 시 - 1. 메뉴 등록하기
	public int addMenu(MenuReqDto mrDto) {
		boolean result = rmDao.registRequestMenu(mrDto);
		if (result) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	// 승인 시 - 2. 요청메뉴 테이블에서 메뉴 삭제하기
	public int deleteMenu(int mrcode) {
		boolean result = rmDao.deleteRequestMenu(mrcode);
		if(result) {
			return 1;
		}else {
			return 0;
		}
	}
}
