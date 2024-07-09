package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.RecipeDto;

import java.util.List;

@Mapper
public interface RecipeDao {
    List<RecipeDto> getRecipeList(int mcode);
}
