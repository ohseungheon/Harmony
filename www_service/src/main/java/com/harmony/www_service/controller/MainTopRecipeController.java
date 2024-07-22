package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.MainTopRecipeDao;
import com.harmony.www_service.dto.PopularRecipeDto;
import com.harmony.www_service.dto.TopViewDto;

@Controller
public class MainTopRecipeController {
	
	@Autowired
	private MainTopRecipeDao mainTopRecipeDao;
	
//	@RequestMapping("/")
//	public String topRecipeView(Model model) {
//		
//		List<TopViewDto> topViewList = mainTopRecipeDao.topView();
//		System.out.println("topViewList : " + topViewList);
//		//List<PopularRecipeDto> popularRecipeList = mainTopRecipeDao.popularRecipe();
//		
//		model.addAttribute("topViewList", topViewList);
//		//model.addAttribute("popularRecipeList", popularRecipeList);
//		//System.out.println(popularRecipeList);
//		
//		return "/main/home";
//	}

}
