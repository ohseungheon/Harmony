package com.harmony.www_service.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.harmony.www_service.dao.RecipeDao;
import com.harmony.www_service.dto.PopularRecipeDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeGetTagDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.service.MemberService;
import com.harmony.www_service.service.RecipeService;

@Controller
public class MenuAllController {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeDao recipeDao;

    @Autowired
    MemberService memberService;

    @GetMapping("/menu_all/menu_all_list")
    public String menu_all_list() {

        return "menu_all/menu_all_list";
    }

    @GetMapping("/menu_all/recipe_all_list")
    public String recipe_all_list(Model model) {

        List<RecipeDto> recipes = recipeDao.getAllRecipes();
        model.addAttribute("recipes", recipes);

        return "menu_all/recipe_all_list";
    }

    @GetMapping("/menu_all/recipe_list")
    public String recipe_list(@RequestParam("mcode") int mcode, Model model) {
        List<RecipeDto> recipeList = recipeService.getRecipeListByMcode(mcode);
        
     // 각 레시피의 마지막 요리 이미지를 가져옵니다.
        Map<Integer, String> lastImgMap = new HashMap<>();
        for (RecipeDto recipe : recipeList) {
            RecipeOrderDto lastImg = recipeDao.recipeLastCookImg(recipe.getRcode());
            lastImgMap.put(recipe.getRcode(), lastImg.getLastCookingImg());
        }
        
        model.addAttribute("mcode", mcode);
        model.addAttribute("recipeList", recipeList);
        model.addAttribute("lastImg", lastImgMap);

        return "menu_all/recipe_list";
    }

    @GetMapping("/menu_all/recipe_detail")
    public String recipe_detail(@RequestParam("rcode") int rcode, Model model) {
        RecipeDto recipeDto = recipeService.getRecipeByRcode(rcode);
        List<RecipeOrderDto> recipeOrderList = recipeService.getRecipeOrdersByRcode(rcode);
        List<RecipeIngredientDto> recipeIngredientList = recipeService.getRecipeIngredientsByRcode(rcode);
        RecipeGetTagDto recipeGetTagDto = recipeDao.recipeGetTag(rcode);
        recipeDao.recipeViewCount(rcode); // 레시피 뷰 카운트 증가

        // security 내부의 로그인 되어 있는 아이디( username ) 불러오기
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // username으로 회원 정보 테이블의 mno 불러오기
        Optional<Integer> mno_ = memberService.getMnoByUsername(username);
        if (mno_.isPresent()) { // 로그인 되어 있으면
            // 누가 어떤 레시피를 조회 했는지 따로 기록
            recipeDao.insert_recent_view(mno_.get(), rcode);
        }

        PopularRecipeDto recipeRecommend = recipeDao.recipeRecommendCount(rcode);

        model.addAttribute("rcode", rcode);
        model.addAttribute("menuName", recipeDto.getMenuName());
        model.addAttribute("view", recipeDto.getView());
        model.addAttribute("portions", recipeDto.getPortions());
        model.addAttribute("recipeName", recipeDto.getRecipeName());
        model.addAttribute("introduce", recipeDto.getIntroduce());
        model.addAttribute("url", recipeDto.getUrl());
        model.addAttribute("recipeOrderList", recipeOrderList);
        model.addAttribute("recipeIngredientList", recipeIngredientList);
        model.addAttribute("recipeGetTagDto", recipeGetTagDto);
        model.addAttribute("recipeRecommend", recipeRecommend);

        return "menu_all/recipe_detail";
    }

    @GetMapping("/menu_all/recomend_menu")
    public String recomend_menu() {

        return "menu_all/recomend_menu";
    }

    @GetMapping("/menu_all/recipe_list_name")
    public String recipeListByName(@RequestParam("menuName") String menuName, Model model) {
        List<RecipeDto> recipeList = recipeService.getRecipeListByMenuName(menuName);

        model.addAttribute("menuName", menuName);
        model.addAttribute("recipeList", recipeList);

        return "menu_all/recipe_list";
    }

}
