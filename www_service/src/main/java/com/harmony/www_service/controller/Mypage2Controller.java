package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;
import com.harmony.www_service.service.MyRecipeService;

@Controller
@RequestMapping("/my/recipe")
public class Mypage2Controller {
	
	@Autowired
	private MyRecipeService service;
	
	@Autowired
	private RecipeMenuDao menuDao;
	
	@RequestMapping("/list")
	public String myRecipeList() {
		
		return "mypage2/my_recipe_list";
	}
	
	@RequestMapping("/registForm")
	public String myRecipeRegist(Model model) {
	
		List<MenuDto> list = menuDao.getMenu();
		
		model.addAttribute("list", list);
		
		return "mypage2/recipe_regist";
	}
	
	@PostMapping("/regist")
	public String recipeRegist(RecipeDto recipeDto,
			RecipeIngredientDto recipeIngredientDto,
			RecipeOrderDto recipeOrderDto,
			RecipeTagDto recipeTagDto) {
		
		service.registMyRecipeService(recipeDto);
		service.registMyRecipeIngredientService(recipeIngredientDto);
		service.registMyRecipeOrderService(recipeOrderDto);
		service.registMyRecipeTagService(recipeTagDto);
		
		
		return "redirect:mypage2/my_recipe_list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
