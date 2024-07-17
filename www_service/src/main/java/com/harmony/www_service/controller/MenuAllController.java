package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.service.RecipeService;

@Controller
public class MenuAllController {
    
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDao recipeDao;

    @GetMapping("/menu_all/menu_all_list")
    public String menu_all_list(){

        return "menu_all/menu_all_list";
    }

    @GetMapping("/menu_all/recipe_all_list")
    public String recipe_all_list(Model model){

        List<RecipeDto> recipes = recipeDao.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "menu_all/recipe_all_list";
    }

    @GetMapping("/menu_all/recipe_list")
    public String recipe_list(@RequestParam("mcode") int mcode, Model model){
        List<RecipeDto> recipeList = recipeService.getRecipeListByMcode(mcode);
        model.addAttribute("mcode", mcode);
        model.addAttribute("recipeList", recipeList);

        return "menu_all/recipe_list";
    }

    @GetMapping("/menu_all/recipe_detail")
    public String recipe_detail(@RequestParam("rcode") int rcode, Model model){
        RecipeDto recipeDto = recipeService.getRecipeByRcode(rcode);
        List<RecipeOrderDto> recipeOrderList = recipeService.getRecipeOrdersByRcode(rcode);
        List<RecipeIngredientDto> recipeIngredientList = recipeService.getRecipeIngredientsByRcode(rcode);

        model.addAttribute("rcode", rcode);
        model.addAttribute("menuName",recipeDto.getMenuName());
        model.addAttribute("recipeName", recipeDto.getRecipeName());
        model.addAttribute("introduce", recipeDto.getIntroduce());
        model.addAttribute("url", recipeDto.getUrl());
        model.addAttribute("recipeOrderList", recipeOrderList);
        model.addAttribute("recipeIngredientList", recipeIngredientList);

        return "menu_all/recipe_detail";
    }

    @GetMapping("/menu_all/recomend_menu")
    public String recomend_menu(){

        return "menu_all/recomend_menu";
    }

    @GetMapping("/menu_all/recipe_list_name")
    public String recipeListByName(@RequestParam("menuName") String menuName, Model model){
        List<RecipeDto> recipeList = recipeService.getRecipeListByMenuName(menuName);

        model.addAttribute("menuName", menuName);
        model.addAttribute("recipeList", recipeList);

        return "menu_all/recipe_list";
    }

}
