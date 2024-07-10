package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.RecipeDto;

@Mapper
public interface MyRecipeDao {
	
	public List<RecipeDto> myRecipe(@Param("mno") int mno);

}
