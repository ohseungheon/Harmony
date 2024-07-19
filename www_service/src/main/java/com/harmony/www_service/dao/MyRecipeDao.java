package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.IngredientDto;
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

	// 재료선택하면 재료의 type 불러오기
	public IngredientDto getTypeByIcode(@Param("icode") int icode);

	// 레시피 수정 Dao
	// 레시피 수정
	public int updateMyRecipe(RecipeDto recipeDto);

	// 레시피 재료 수정
	public int updateMyRecipeIngredient(RecipeIngredientDto recipeIngredientDto);

	// 레시피 요리순서 수정
	public int updateMyRecipeOrder(RecipeOrderDto recipeOrderDto);

	// 레시피 태그 수정
	public int updateMyRecipeTag(RecipeTagDto recipeTagDto);

	// 레시피 수정을 위한 정보들
	// 레시피 수정을 위한 레시피 정보
	public RecipeDto getRecipeFindByRcode(@Param("rcode") int rcode);

	// 레시피 수정을 위한 레시피 재료 정보
	public List<RecipeIngredientDto> getRecipeIngredientFindByRcode(@Param("rcode") int rcode);

	// 레시피 수정을 위한 레시피 요리순서 정보
	public List<RecipeOrderDto> getRecipeOrderFindByRcode(@Param("rcode") int rcode);

	// 레시피 수정을 위한 레시피 태그 정보
	public RecipeTagDto getRecipeTagFindByRcode(@Param("rcode") int rcode);

	public int deleteMyRecipeIngredient(int ricode);

	public int deleteMyRecipeOrder(int rocode);
	
	// 재료정보 리스트
	public List<IngredientDto> getIngredient();
	
	
	

}
