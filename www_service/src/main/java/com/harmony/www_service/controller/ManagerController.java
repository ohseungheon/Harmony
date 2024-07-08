package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {
    
	@RequestMapping("/ingredient_regist")
	public String ingredientRegist() {
		return "manager/ingredients_regist";
	}
}