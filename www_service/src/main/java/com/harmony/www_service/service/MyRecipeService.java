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
	
	// 레시피 수정 Service
	// 레시피 수정
	public int updateMyRecipeService(RecipeDto recipeDto) {
		
		return dao.updateMyRecipe(recipeDto);
	}
	
	// 레시피 재료 수정
	public int updateMyRecipeIngredientService(RecipeIngredientDto recipeIngredientDto) {
		
		return dao.updateMyRecipeIngredient(recipeIngredientDto);
	}
	
	// 레시지 요리순서 수정
	public int updateMyRecipeOrderService(RecipeOrderDto recipeOrderDto) {
		
		return dao.updateMyRecipeOrder(recipeOrderDto);
	}
	
	// 레시피 태그 수정
	public int updateMyRecipeTagService(RecipeTagDto recipeTagDto) {
		
		return dao.updateMyRecipeTag(recipeTagDto);
	}
	
	
	// 레시피 수정을 위한 데이터 보내기
	// 레시피 수정을 위한 레시피 정보
	public RecipeDto getRecipeService(int rcode) {
		
		return dao.getRecipeFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 재료 정보
	public List<RecipeIngredientDto> getRecipeIngredientService(int rcode){
		
		return dao.getRecipeIngredientFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 요리순서 정보
	public List<RecipeOrderDto> getRecipeOrderService(int rcode){
		
		return dao.getRecipeOrderFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 태그 정보
	public RecipeTagDto getRecipeTagService(int rcode) {
		
		return dao.getRecipeTagFindByRcode(rcode);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
