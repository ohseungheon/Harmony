package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

@Mapper
public interface Menu1Dao {
	List<IngredientDto> showFridgeIngredient();
	MenuDto showCanMakeMenu();
}
