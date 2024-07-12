package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.MyRecipeDao;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;

@Service
public class MyRecipeService {
	
	@Autowired
	private MyRecipeDao dao;
	
	// 나의 레시피 리스트
	public List<RecipeDto> myRecipeService(int mno){
		
		List<RecipeDto> list = dao.myRecipe(mno);
		
		return list;
	}
	
	// 나의 레시피 삭제
	public int deleteMyRecipe(int rcode) {
		
		int result = dao.deleteMyRecipe(rcode);
		
		return result;
	}
	
	// 레시피 등록
	public int registMyRecipeService(RecipeDto recipeDto) {
		
		int resultRecipe = dao.registMyRecipe(recipeDto);
		
		return resultRecipe;
	}

	// 레시피 재료 등록
	public int registMyRecipeIngredientService(RecipeIngredientDto recipeIngredientDto) {
		
		int resultRecipeIngredient = dao.registMyRecipeIngredient(recipeIngredientDto);
		
		return resultRecipeIngredient;
	}
	
	// 레시피 요리순서 등록
	public int registMyRecipeOrderService(RecipeOrderDto recipeOrderDto) {
		
		int resultRecipeOrder = dao.registMyRecipeOrder(recipeOrderDto);
		
		return resultRecipeOrder;
	}
	
	// 레시피 태그 등록
	public int registMyRecipeTagService(RecipeTagDto recipeTagDto) {
		
		int resultRecipeTag = dao.registMyRecipeTag(recipeTagDto);
		
		return resultRecipeTag;
	}
	
	
	
	
	
	
	
	
	
}
