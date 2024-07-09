package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.IngredientDto;

@Mapper
public interface IIngredientDao {

	// 재료 등록
	public void registIngredient(@Param("iDto") IngredientDto iDto);
	// 재료 수정
	public IngredientDto updateIngredient(@Param("iDto") IngredientDto iDto);
	// 재료 삭제
	public void deleteIgredient(@Param("icode") int icode);
	// 재료 리스트 조회
	public List<IngredientDto> getList(@Param("category") IngredientDto ingredientDto);
	// 재료 상세 조회
	public IngredientDto getDetail(@Param("icode") int icode);
	
}
