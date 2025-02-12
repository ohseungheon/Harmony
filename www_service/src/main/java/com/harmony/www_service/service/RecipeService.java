package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;


@Service
public class RecipeService {
    @Autowired
    private RecipeDao recipeDao;

    public List<RecipeDto> getRecipeListByMcode(int mcode){
        
        return recipeDao.getRecipeListByMcode(mcode);
    }

    public RecipeDto getRecipeByRcode(int rcode){

        return recipeDao.getRecipeByRcode(rcode);
    }

    public List<RecipeOrderDto> getRecipeOrdersByRcode(int rcode){

        return recipeDao.getRecipeOrdersByRcode(rcode);
    }

    public List<RecipeIngredientDto> getRecipeIngredientsByRcode(int rcode){

        return recipeDao.getRecipeIngredientsByRcode(rcode);
    }

    public List<RecipeDto> getRecipeListByMenuName(String menuName){
        
        return recipeDao.getRecipeListByMenuName(menuName);
    }

    public List<RecipeDto> getAllRecipes(){

        return recipeDao.getAllRecipes();
    }

    public List<String> getRecipeCategories(){

        return recipeDao.getRecipeCategories();
    }

    public List<String> getAllThemes() {

        return recipeDao.getAllThemes();
    }

    public List<RecipeDto> getFilteredRecipeList(List<String> category, List<Integer> ingredient, List<String> theme, String searchTerm) {
        if ((category == null || category.isEmpty()) && 
            (ingredient == null || ingredient.isEmpty()) && 
            (theme == null || theme.isEmpty()) &&
            (searchTerm == null || searchTerm.isEmpty())) {
            return recipeDao.getAllRecipes();
        }
        return recipeDao.getFilteredRecipes(category, ingredient, theme, searchTerm);
    }

    public List<RecipeDto> searchRecipe(String term) {
        return recipeDao.searchRecipe(term);
    }
}
