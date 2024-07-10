package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;

import java.util.List;


@Service
public class RecipeService {
    @Autowired
    private RecipeDao recipeDao;

    public List<RecipeDto> getRecipeList(int mcode){
        
        return recipeDao.getRecipeList(mcode);
    }

    public List<RecipeOrderDto> getRecipeOrder(int rcode){

        return recipeDao.getRecipeOrder(rcode);
    }

    public List<RecipeIngredientDto> getRecipeIngredient(int rcode){

        return recipeDao.getRecipeIngredient(rcode);
    }

}
