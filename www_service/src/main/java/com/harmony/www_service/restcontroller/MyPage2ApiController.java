package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.service.RecipeMenuService;

@RestController
public class MyPage2ApiController {
	
	@Autowired
	private RecipeMenuService recipeMenuService;
	
	@GetMapping("/getMenuImage")
	public MenuDto getMenuImage(@RequestParam("mcode") int mcode) {
		
		MenuDto resultUrl = recipeMenuService.getImgUrlService(mcode);
		System.out.println(resultUrl);
		
		return resultUrl;
	}

}
