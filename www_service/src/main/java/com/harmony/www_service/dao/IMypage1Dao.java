package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.FridgeIngredientDto;

@Mapper
public interface IMypage1Dao {
	//냉동
	public List<FridgeIngredientDto> getIceList(@Param("mno") int mno);
	//냉장
	public List<FridgeIngredientDto> getCoolList(@Param("mno") int mno);
	//상온
	public List<FridgeIngredientDto> getFoodList(@Param("mno") int mno);
	
	//ALL 냉장
	public List<FridgeIngredientDto> getAllIceList(@Param("mno") int mno);
	//ALL 냉동
	public List<FridgeIngredientDto> getAllCoolList(@Param("mno") int mno);
	//ALL 상온
	public List<FridgeIngredientDto> getAllFoodList(@Param("mno") int mno);

	// 싹 다~ 가져오기
	public List<FridgeIngredientDto> getAllList(@Param("mno") int mno);
}
