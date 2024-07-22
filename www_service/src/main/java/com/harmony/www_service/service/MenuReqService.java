package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IRequestMenuDao;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.MenuReqDto;

@Service
public class MenuReqService {

	@Autowired
	IRequestMenuDao rmDao;

	// 전체 요청 메뉴 리스트 불러오기
	public List<MenuReqDto> getReqMenuList() {
		System.out.println("---------- menuReq service 진입 ----------");
		List<MenuReqDto> list = rmDao.findMenuRequest();

		System.out.println("================== list : " + list);
		return list;
	}

	// 요청 메뉴 디테일
	public MenuReqDto getDetail(int mrcode) {
		return rmDao.findById(mrcode);
	}
	// 승인 시 - 메뉴 등록하기
	public int addMenu(MenuDto mDto) {
		boolean result = rmDao.registRequestMenuToMenu(mDto);
		if (result) {
			return 1;
		}else {
			return 0;
		}
		
	}
	
	// 승인 & 반려 시 - 요청메뉴 테이블에서 메뉴 삭제하기
	public int deleteMenu(int mrcode) {
		boolean result = rmDao.deleteRequestMenu(mrcode);
		if(result) {
			return 1;
		}else {
			return 0;
		}
	}
	
	
}
