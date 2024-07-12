package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.MenuDto;

@Mapper
public interface RecipeMenuDao {

	// 레시피 등록에서 메뉴 불러오기
	public List<MenuDto> getMenu();
	
}
