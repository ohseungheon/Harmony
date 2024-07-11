package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my/recipe")
public class Mypage2Controller {
	
	@RequestMapping("/list")
	public String myRecipeList() {
		
		return "mypage2/my_recipe_list";
	}
	
	@RequestMapping("/regist")
	public String myRecipeRegist() {
	
		
		return "mypage2/recipe_regist";
	}
	
	
	
}
