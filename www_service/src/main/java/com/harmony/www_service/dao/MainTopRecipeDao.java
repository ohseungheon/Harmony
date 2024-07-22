package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.PopularRecipeDto;
import com.harmony.www_service.dto.TopViewDto;

@Mapper
public interface MainTopRecipeDao {

//	최다 조회 레시피 top5
	public List<TopViewDto> topView();
	
//	인기 레시피 top5
	public List<PopularRecipeDto> popularRecipe();
}
