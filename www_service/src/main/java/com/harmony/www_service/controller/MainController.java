package com.harmony.www_service.controller;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.MainTopRecipeDao;
import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.MemberDto;
import com.harmony.www_service.dto.PopularRecipeDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.TopViewDto;
import com.harmony.www_service.service.LoginService;

@Controller
public class MainController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MainTopRecipeDao mainTopRecipeDao;
	
	@Autowired
	private RecipeDao recipeDao;
	
	 @RequestMapping("/")
	    public String root(Model model) {
	        
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	        
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        String role = null;
	        if (authorities != null && !authorities.isEmpty()) {
	            Iterator<? extends GrantedAuthority> iter = authorities.iterator();
	            if (iter.hasNext()) {
	                GrantedAuthority auth = iter.next();
	                role = auth.getAuthority();
	            }
	        }
	        
	        MemberDto memberDto = loginService.getMnoByUsernameService(username);
	        if (memberDto != null) {
	            model.addAttribute("mno", memberDto.getMno());
	        } else {
	            model.addAttribute("mno", "No mno found");
	        }
	        
	        model.addAttribute("username", username);
	        model.addAttribute("role", role != null ? role : "No role found");
	        
	        // 최다 조회수, 인기 레시피
	        List<TopViewDto> topViewList = mainTopRecipeDao.topView();
			List<PopularRecipeDto> popularRecipeList = mainTopRecipeDao.popularRecipe();
			
			model.addAttribute("topViewList", topViewList);
			model.addAttribute("popularRecipeList", popularRecipeList);
			
			// 레시피 영상
			List<RecipeDto> recipeDto = recipeDao.recipeGetUrl();
			
			model.addAttribute("recipeDto", recipeDto);
			
	        return "main/home";
	    }
	 
	 
	 
	 
	 
	 
}
