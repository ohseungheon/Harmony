package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;

import java.util.List;

@Mapper
public interface RecipeDao {
    List<RecipeDto> getRecipeList(int mcode);
    List<RecipeIngredientDto> getRecipeIngredient(int rcode);
    List<RecipeOrderDto> getRecipeOrder(int rcode);
}
