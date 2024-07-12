package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;

@Mapper
public interface MyRecipeDao {
	
	// 나의 레시피 리스트
	public List<RecipeDto> myRecipe(@Param("mno") int mno);
	
	// 나의 레시피 삭제
	public int deleteMyRecipe(@Param("rcode") int rcode);
	
	// 레시피 등록
	public int registMyRecipe(RecipeDto recipeDto);
	
	// 레시피 재료 등록
	public int registMyRecipeIngredient(RecipeIngredientDto recipeIngredientDto);
	
	// 레시피 요리순서 등록
	public int registMyRecipeOrder(RecipeOrderDto recipeOrderDto);
	
	// 레시피 태그 등록
	public int registMyRecipeTag(RecipeTagDto recipeTagDto);

}
