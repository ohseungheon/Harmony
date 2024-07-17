package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.service.IngredientService;
import com.harmony.www_service.service.MenuListService;
import com.harmony.www_service.service.MenuService;
import com.harmony.www_service.service.RecipeService;

import java.util.List;

import com.harmony.www_service.dto.IngredientDto;

@RestController
@RequestMapping("/api/menu")
public class MenuAllRestController {
    @Autowired
    private MenuListService menuListService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/menu_all_list")
    public List<MenuDto> getMenuListByCategory(@RequestParam("category") String category){

        return menuListService.getMenuListByCategory(category);
    }

    @GetMapping("/filtered_menu_list")
    public List<MenuDto> getFilteredMenuList(
        @RequestParam(value = "categories", required = false) String categories,
        @RequestParam(value = "ingredients", required = false) String ingredients) {
        
        return menuService.getFilteredMenuList(categories, ingredients);
    }

    @GetMapping("/ingredients")
    public List<IngredientDto> getAllIngredients(){

        return ingredientService.gettAllIngredients();
    }

    @GetMapping("/ingredient-categories")
    public List<String> getIngredientCategories(){

        return ingredientService.gettAllIngredientCategories();
    }

    @GetMapping("/recipeAllList")
	public List<RecipeDto> getAllRecipes(){
        System.out.println("getAllRecipes 메서드가 호출되었습니다.");
		return recipeService.getAllRecipes();
	}
}
