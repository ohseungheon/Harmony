package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDtoWithFile;
import com.harmony.www_service.service.IngredientService_dally;

@Controller
public class ManagerController {
    
	@Autowired
	IngredientService_dally iService;
	
	// 재료 등록 페이지
	@RequestMapping("/ingredients_regist")
	public String goRegist() {
		
		return "manager/ingredients_regist";
	}
	
	// 재료 등록 기능
	@PostMapping("/do_ingredients_regist")
	public String ingredientRegist(IngredientDtoWithFile iDtoFile) {
		System.out.println("--------post--------");
		
		iService.addIngredients(iDtoFile);
		return "redirect:/ingredients_list";
	}
	
//	@RequestMapping("ingredient_update")
//	public void updateIngredient(@RequestParam IngredientDto iDto, Model model) {
//		System.out.println("--------update--------");
//		
//	}
	
	// 재료 리스트 페이지
	@RequestMapping("/ingredients_list")
	public String goList() {
		return "manager/ingredients_list";
	}

	
	
}