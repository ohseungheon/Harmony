package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.PopularRecipeDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeGetTagDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;

@Mapper
public interface RecipeDao {
    List<RecipeDto> getRecipeListByMcode(int mcode);

    RecipeDto getRecipeByRcode(int rcode);

    List<RecipeIngredientDto> getRecipeIngredientsByRcode(int rcode);

    List<RecipeOrderDto> getRecipeOrdersByRcode(int rcode);
    
    List<RecipeDto> getRecipeListByMenuName(String menuName);

    List<RecipeDto> getAllRecipes();

    List<String> getRecipeCategories();

    List<RecipeDto> getFilteredRecipes(@Param("category") List<String> category, @Param("ingredient") List<Integer> ingredient);

    List<RecipeDto> searchRecipe(String term);
    
//    레시피 태그 가져오기
    RecipeGetTagDto recipeGetTag(@Param("rcode") int rcode);
    
//    레시피 디테일 들어갈때마다 view +1
    RecipeDto recipeViewCount(@Param("rcode") int rcode);
    
//    레시피 디테일 좋아요수
    PopularRecipeDto recipeRecommendCount(@Param("rcode") int rcode);
}
