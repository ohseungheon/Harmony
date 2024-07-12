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
	
	// 재료 리스트 페이지
	@RequestMapping("/ingredients_list")
	public String goList() {
		return "manager/ingredients_list";
	}


	// 재료 디테일 페이지
	@RequestMapping("/ingredient_detail")
	public String goDetail() {
		return "manager/ingredients_detail";
	}
	
	// 재료 디테일 기능 구현
	@RequestMapping("/do_ingredient_detail")
	public IngredientDto showDetail(@RequestParam("icode") int icode) {
		IngredientDto result = iService.showDetail(icode);
		return result;
	} 
	
	
//	// 재료 수정 기능 구현
//	@RequestMapping("/do_ingredient_update")
//	public IngredientDto update(IngredientDtoWithFile iDtoFile, Model model){
//		System.out.println("--------update--------");
//		return  ;
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
}