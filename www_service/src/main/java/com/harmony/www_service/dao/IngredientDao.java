package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.IngredientDto;

import java.util.List;

@Mapper
public interface  IngredientDao {
    List<IngredientDto> getAllIngredients();

    List<String> getAllIngredientCategories();
}
