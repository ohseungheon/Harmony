package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.RecipeDto;
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

    List<RecipeDto> getFilteredRecipes(@Param("categories") List<String> categories, @Param("ingredients") List<Integer> ingredients);
}
