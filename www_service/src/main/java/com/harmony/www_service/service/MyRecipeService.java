package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.MyRecipeDao;
import com.harmony.www_service.dto.RecipeDto;

@Service
public class MyRecipeService {
	
	@Autowired
	private MyRecipeDao dao;
	
	public List<RecipeDto> myRecipeService(int mno){
		
		
		
		List<RecipeDto> list = dao.myRecipe(mno);
		
		return list;
	}

}
