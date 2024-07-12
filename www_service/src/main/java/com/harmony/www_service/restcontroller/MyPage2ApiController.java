package com.harmony.www_service.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.IngredientDto;
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
	
	@GetMapping("/getTypeByIcode")
	public ResponseEntity<Map<String, String>> getTypeByIcode(@RequestParam("icode") int icode) {
		
		IngredientDto result = recipeMenuService.getTypeByIcode(icode);
		Map<String, String> response = new HashMap<>();
		response.put("type", result.getType());
		
		return ResponseEntity.ok(response);
	}

}
