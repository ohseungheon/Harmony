package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.harmony.www_service.dao.IngredientDao;
import com.harmony.www_service.dto.IngredientDto;

@Service
public class IngredientService {
    
    @Autowired
    private IngredientDao ingredientDao;

    public List<IngredientDto> gettAllIngredients(){

        return ingredientDao.getAllIngredients();
    }

    
}
