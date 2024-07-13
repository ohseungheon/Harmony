package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public String goDetail(@RequestParam("icode") int icode, Model model) {
		System.out.println("디테일 페이지 넘어옴"+icode);
		
		IngredientDto result = iService.showDetail(icode);
		
		System.out.println("컨트롤러에서 디테일 결과 확인 : " + result);
		model.addAttribute("detail", result);
		
		return "manager/ingredient_detail";
	}
	
	
	// 재료 수정 페이지
	@RequestMapping("/ingredient_update")
	public String updatePage(@RequestParam("icode") int icode, Model model) {
		System.out.println("------ 재료 수정을 위한 디테일 전송 ------");
		IngredientDto result = iService.showDetail(icode);
		model.addAttribute("detail", result);

		return "manager/ingredients_update";
	}
	
	// 재료 수정 기능
	@RequestMapping("/do_ingredient_update")
	public String update(IngredientDtoWithFile iDtoFile){
		System.out.println("--------update--------");
		iService.updateIngredients(iDtoFile);
		int icode= iDtoFile.getIcode();
		System.out.println("****************iDtoFile : " + iDtoFile);
		
		return "redirect:/ingredient_detail?icode="+icode;
	}

	
	// 재료 삭제 기능 구현
	@RequestMapping("/do_ingredient_delete")
	public String delete(@RequestParam("icode") int icode) {
		iService.IgredientIsGone(icode);
		
		return "redirect:/ingredients_list";
	}
	
	
	
	
}