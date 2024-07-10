package com.harmony.www_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.service.MenuService;

@Controller
@RequestMapping("/menu1")


public class Menu1Controller {
	@Autowired
	Menu1Dao menu1dao;
	@Autowired
	MenuService menuService;
	//private static List<IngredientDto> InFridgeIngredientList= new ArrayList<>();
	private static List<IngredientDto> NoInFridgeIngredientList= new ArrayList<>();

	@RequestMapping("main")
	public String main(org.springframework.ui.Model model) {
		List<IngredientDto> FridgeIngredientList = menu1dao.showFridgeIngredient();
		model.addAttribute("FridgeIngredientList",FridgeIngredientList);
		System.out.println("======================FridgeIngredientList : "+FridgeIngredientList);
		
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(FridgeIngredientList);
		System.out.println("======================showCanMakeMenu : "+showCanMakeMenuList);
    	model.addAttribute("showCanMakeMenuList",showCanMakeMenuList);
    	int FridgeIngredientListSize =FridgeIngredientList.size();
    	model.addAttribute("FridgeIngredientListSize",FridgeIngredientListSize);
    	System.out.println("======================FridgeIngredientListSize : "+FridgeIngredientListSize);
    	
		return "menu1/menu_harmony1";
    }
    
	@RequestMapping("start")
	public String start(org.springframework.ui.Model model) {
    	return "mypage1/mypage_main";
    }
    

	
	@ResponseBody
	@GetMapping("deleteFridgeIngredientList")
	public List<IngredientDto> deleteFridgeIngredientList(@RequestParam("icode") int icode,org.springframework.ui.Model model) {
		System.out.println("===========================deleteFridgeIngredientList=========================");
		
		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
		NoInFridgeIngredientList.add(getOneIngredient);
		
		for (int i =0 ;i<NoInFridgeIngredientList.size();i++) {
			
			
			System.out.println("=================NoInFridgeIngredientList==================: "+NoInFridgeIngredientList.get(i));
		}
		
		//InFridgeIngredientList
		List<IngredientDto> selectExcludeIngredientList = menuService.selectExcludeIngredient(NoInFridgeIngredientList);
		
		//List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);
		
		//System.out.println("=================InFridgeIngredientList==================: "+InFridgeIngredientList);
		System.out.println("=================NoInFridgeIngredientList==================: "+NoInFridgeIngredientList);
		//System.out.println("=================showCanMakeMenuList==================: "+showCanMakeMenuList);
    	return selectExcludeIngredientList;
    }
    
    
	@ResponseBody
	@GetMapping("deleteCanMakeMenu")
	public List<MenuDto> deleteCanMakeMenu(@RequestParam("icode") int icode,org.springframework.ui.Model model) {
		System.out.println("===========================deleteCanMakeMenu=========================");
		List<IngredientDto> InFridgeIngredientList = menu1dao.showFridgeIngredient();
		IngredientDto getOneIngredient = menuService.getOneIngredient(icode);

		InFridgeIngredientList.remove(getOneIngredient);
		
		for (int i =0 ;i<NoInFridgeIngredientList.size();i++) {
			
			System.out.println("=================InFridgeIngredientList==================: "+InFridgeIngredientList.get(i));
		}
		
		//InFridgeIngredientList
		//List<IngredientDto> selectExcludeIngredientList = menuService.selectExcludeIngredient(NoInFridgeIngredientList);
		
		List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);
		
		//System.out.println("=================InFridgeIngredientList==================: "+InFridgeIngredientList);
		//System.out.println("=================NoInFridgeIngredientList==================: "+NoInFridgeIngredientList);
		System.out.println("=================showCanMakeMenuList==================: "+showCanMakeMenuList);
    	return showCanMakeMenuList;
    }

	
}
