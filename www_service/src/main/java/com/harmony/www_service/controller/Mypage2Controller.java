package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dao.RecipeMenuDao;
import com.harmony.www_service.dto.MenuDto;
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
	
	//@PostMapping("/regist")
//	public String recipeRegist(@RequestParam("mcode") int mcode,
//			@RequestParam("recipeName") String recipeName,
//			@RequestParam("introduce") String introduce,
//			@RequestParam("url") String url,
//			@RequestParam("category") String category,
//			@RequestParam("portions") String protions,
//			@RequestParam("icode") int icode,
//			@RequestParam("amount") int amount
//			) {
		
		//service.registMyRecipeService(recipeDto);
		//service.registMyRecipeIngredientService(recipeIngredientDto);
		//service.registMyRecipeOrderService(recipeOrderDto);
		//service.registMyRecipeTagService(recipeTagDto);
		
		
		//return "redirect:mypage2/my_recipe_list";
	//}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
