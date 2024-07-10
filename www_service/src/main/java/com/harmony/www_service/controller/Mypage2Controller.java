package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class Mypage2Controller {
	
	@RequestMapping("/myRecipeList")
	public String myRecipeList() {
		
		return "mypage2/my_recipe_list";
	}
    
}
