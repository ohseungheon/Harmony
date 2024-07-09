package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.IngredientDto;
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
	public String ingredientRegist(IngredientDto iDto, Model model) {
		System.out.println("--------post--------");
		iService.save(iDto);
		model.addAttribute("msg", "등록되었습니다!");
		return "redirect: manager/ingredients_regist";
	}
	
//	@RequestMapping("ingredient_update")
//	public void updateIngredient(@RequestParam IngredientDto iDto, Model model) {
//		System.out.println("--------update--------");
//		
//	}
	
}