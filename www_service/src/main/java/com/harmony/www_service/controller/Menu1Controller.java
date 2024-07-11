package com.harmony.www_service.controller;

import java.security.Provider.Service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.service.MenuService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/menu1")


public class Menu1Controller {
	@Autowired
	Menu1Dao menu1dao;
	@Autowired
	MenuService menuService;
	//private static List<IngredientDto> InFridgeIngredientList= new ArrayList<>();
	//private static List<IngredientDto> NoInFridgeIngredientList= new ArrayList<>();

	  @RequestMapping("main")
	    public String main(org.springframework.ui.Model model, HttpSession session) {
	        session.removeAttribute("NoInFridgeIngredientList");
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			int mno = menu1dao.getMno(username);
			System.out.println("==============================================================mno"+mno);
	        List<IngredientDto> FridgeIngredientList = menu1dao.showFridgeIngredient(mno);
	        model.addAttribute("FridgeIngredientList", FridgeIngredientList);
	        List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(FridgeIngredientList);
	        model.addAttribute("showCanMakeMenuList", showCanMakeMenuList);

	        int FridgeIngredientListSize = FridgeIngredientList.size();
	        model.addAttribute("FridgeIngredientListSize", FridgeIngredientListSize);

	        return "menu1/menu_harmony1";
	    }
    
	@RequestMapping("start")
	public String start(org.springframework.ui.Model model) {
    	return "mypage1/mypage_main";
    }
    
	@ResponseBody
	@RequestMapping("reset")
	public String reset(org.springframework.ui.Model model,HttpSession session) {
        session.removeAttribute("NoInFridgeIngredientList");

		return "mypage1/mypage_main";
	}
	

	
	  @ResponseBody
	    @GetMapping("deleteFridgeIngredientList")
	    public List<IngredientDto> deleteFridgeIngredientList(@RequestParam("icode") int icode, HttpSession session) {
	        System.out.println("===========================deleteFridgeIngredientList=========================");
	        
	        List<IngredientDto> NoInFridgeIngredientList = (List<IngredientDto>) session.getAttribute("NoInFridgeIngredientList");
	        if (NoInFridgeIngredientList == null) {
	            NoInFridgeIngredientList = new ArrayList<>();
	        }

	        IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
	        NoInFridgeIngredientList.add(getOneIngredient);
	        session.setAttribute("NoInFridgeIngredientList", NoInFridgeIngredientList);

	        List<IngredientDto> selectExcludeIngredientList = menuService.selectExcludeIngredient(NoInFridgeIngredientList);

	        System.out.println("=================NoInFridgeIngredientList==================: " + NoInFridgeIngredientList);
	        
	        return selectExcludeIngredientList;
	    }

    
    
	  @ResponseBody
	    @GetMapping("deleteCanMakeMenu")
	    public List<MenuDto> deleteCanMakeMenu(@RequestParam("icode") int icode, HttpSession session) {
	        System.out.println("===========================deleteCanMakeMenu=========================");
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
			int mno = menu1dao.getMno(username);
	        List<IngredientDto> InFridgeIngredientList = menu1dao.showFridgeIngredient(mno);
	        IngredientDto getOneIngredient = menuService.getOneIngredient(icode);
	        InFridgeIngredientList.remove(getOneIngredient);

	        List<MenuDto> showCanMakeMenuList = menuService.getCanMakeMenu(InFridgeIngredientList);

	        System.out.println("=================showCanMakeMenuList==================: " + showCanMakeMenuList);
	        
	        return showCanMakeMenuList;
	    }

	
}
