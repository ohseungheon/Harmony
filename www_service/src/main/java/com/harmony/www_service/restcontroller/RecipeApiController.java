package com.harmony.www_service.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.service.MemberService;
import com.harmony.www_service.service.MyRecipeService;
import com.harmony.www_service.service.RecipeService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeApiController {
	
	@Autowired
	private MemberService memberService;

	@Autowired
	private RecipeService recipeService;
	
	@Autowired
	private MyRecipeService myRecipeService;
	
	// 나의 레시피 리스트
	@GetMapping("/recipeList")
	public List<RecipeDto> recipeList(@RequestParam("mno") int mno) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		
		System.out.println(mno);
		
		List<RecipeDto> list = myRecipeService.myRecipeService(mno);
		System.out.println(list);
		
		return list;
	}
	
	// 레시피 삭제
	@DeleteMapping("/recipeDelete")
	public int recipeDelete(@RequestParam("rcode") int rcode) {
		
		return myRecipeService.deleteMyRecipe(rcode);
	}
	
	@GetMapping("/recipeAllList")
	public List<RecipeDto> getAllRecipes(){

		return recipeService.getAllRecipes();
	}

	

}
