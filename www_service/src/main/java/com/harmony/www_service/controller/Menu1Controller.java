package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

@Controller
@RequestMapping("/menu1")


public class Menu1Controller {
	@Autowired
	Menu1Dao menu1dao;

	@RequestMapping("main")
	public String main(org.springframework.ui.Model model) {
		List<IngredientDto> FridgeIngredientList = menu1dao.showFridgeIngredient();
		model.addAttribute("FridgeIngredientList",FridgeIngredientList);
		
		MenuDto showCanMakeMenu = menu1dao.showCanMakeMenu();
		System.out.println("======================showCanMakeMenu : "+showCanMakeMenu);
    	model.addAttribute("showCanMakeMenu",showCanMakeMenu);
		return "menu1/menu_harmony1";
    }
    
	@RequestMapping("start")
	public String start(org.springframework.ui.Model model) {
    	return "mypage1/mypage_main";
    }
    

	
	
	@RequestMapping("deleteFridgeIngredientList")
	public String deleteFridgeIngredientList(org.springframework.ui.Model model) {
    	return "menu1/menu_list2";
    }
    
    


	
}
