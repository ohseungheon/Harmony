package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.service.RecipeService;

@Controller
public class MenuAllController {
    
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/menu_all/menu_all_list")
    public String menu_all_list(){

        return "menu_all/menu_all_list";
    }

    @GetMapping("/menu_all/recipe_list")
    public String recipe_list(@RequestParam("mcode") int mcode, Model model){
        List<RecipeDto> recipeList = recipeService.getRecipeList(mcode);
        model.addAttribute("mcode", mcode);
        model.addAttribute("recipeList", recipeList);
        
        return "menu_all/recipe_list";
    }



}
