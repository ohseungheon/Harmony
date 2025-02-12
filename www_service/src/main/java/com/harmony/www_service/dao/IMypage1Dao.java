package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.FridgeIngredientDto;
import com.harmony.www_service.dto.IngredientDto;

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
	
	// 싹 가져오는데 유통기한 임박한거 10개만 가져오기 
	public List<FridgeIngredientDto> getAllDayList(@Param("mno") int mno);
	
	//등록했던 재료 삭제 
	public void deleteMaterial(@Param("fcode") int fcode);
	
	//등록했던 재료 수정 위한 조회
	public FridgeIngredientDto getMaterialList(@Param("fcode") int fcode);
	
	//등록했던 재료 수정 기능 
	public void updateMaterial(@Param("material") FridgeIngredientDto material);
	
	//fcode로 해당 재료 데이터 가져오기 
	public FridgeIngredientDto getIngredientByFcode(@Param("fcode") int fcode);
	
	//재료등록
	public void insertFridge(@Param("fi") FridgeIngredientDto fi);
	
	//재료전체조회
	public List<IngredientDto> allIngredientList();
}
