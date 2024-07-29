package com.harmony.www_service.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeAllResponseDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.WeatherMenuDto;
import com.harmony.www_service.service.IngredientService;
import com.harmony.www_service.service.MenuListService;
import com.harmony.www_service.service.MenuService;
import com.harmony.www_service.service.RecipeService;
import com.harmony.www_service.service.WeatherMenuService;

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
    
    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    private WeatherMenuService weatherMenuService;

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


    @GetMapping("/recipe-categories")
    public List<String> getRecipeCategories(){

        return recipeService.getRecipeCategories();
    }
    
    @GetMapping("/filtered_recipe_list")
    public ResponseEntity<RecipeAllResponseDto> getFilteredRecipeList(
        @RequestParam(value = "category", required = false) List<String> category,
        @RequestParam(value = "ingredient", required = false) List<Integer> ingredient,
        @RequestParam(value = "theme", required = false) List<String> theme,
        @RequestParam(value = "searchTerm", required = false) String searchTerm) {
    	
    	List<RecipeDto> recipes = recipeService.getFilteredRecipeList(category, ingredient, theme, searchTerm);

        Map<Integer, String> lastImgMap = new HashMap<>();
        for (RecipeDto recipe : recipes) {
            RecipeOrderDto lastImg = recipeDao.recipeLastCookImg(recipe.getRcode());
            lastImgMap.put(recipe.getRcode(), lastImg.getLastCookingImg());
        }

        RecipeAllResponseDto response = new RecipeAllResponseDto();
        response.setRecipes(recipes);
        response.setLastImgMap(lastImgMap);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public List<MenuDto> searchMenus(@RequestParam("term") String term) {
        return menuService.searchMenus(term);
    }

    @GetMapping("/searchRecipe")
    public List<RecipeDto> searchRecipe(@RequestParam("term") String term){
        return recipeService.searchRecipe(term);
    }

    @GetMapping("/menu_all")
    public List<MenuDto> getAllMenus(){

        return menuListService.getAllMenus();
    }

    @GetMapping("/recipe-themes")
    public List<String> getRecipeThemes(){

        return recipeService.getAllThemes();
    }

    @GetMapping("/weather_menu")
    public WeatherMenuDto getWeatherMenuRecommendation(){
        return weatherMenuService.getWeatherMenuRecommendation();
    }

}
