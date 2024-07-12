package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.MyRecipeDao;
import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

@Service
public class RecipeMenuService {
	
	@Autowired
	private RecipeMenuDao dao;
	
	@Autowired
	private MyRecipeDao myDao;
	
	public MenuDto getImgUrlService(int mcode) {
		
		MenuDto resultImg = dao.getImageUrlByMcode(mcode);
		
		return resultImg;
		
	}
	
	public IngredientDto getTypeByIcode(int icode) {
		
		IngredientDto result = myDao.getTypeByIcode(icode);
		
		return result;
	}

}
