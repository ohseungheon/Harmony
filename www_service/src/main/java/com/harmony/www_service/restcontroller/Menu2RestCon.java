package com.harmony.www_service.restcontroller;

import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.IMypage1Dao;
import com.harmony.www_service.dto.FridgeIngredientDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.service.MemberService;
import com.harmony.www_service.service.Menu2Service;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu2-api")
public class Menu2RestCon {

    @Autowired
    IMypage1Dao myIngredientDao;

    @Autowired
    MemberService memberService;

    @Autowired
    Menu2Service service;

    @GetMapping("/my-ingredients")
    public ResponseEntity<?> getMyIngredients() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Integer> mno_ = memberService.getMnoByUsername(username);

        if (mno_.isPresent()) {
            List<FridgeIngredientDto> ingredients = myIngredientDao.getAllList(mno_.get());
            return ResponseEntity.ok().body(ingredients);
        } else {
            System.out.println("Recommend_Cooking_Exception: 회원으로 확인 되지 않은 사용자가 시도함.");
            return ResponseEntity.badRequest().body("회원으로 확인 되지 않은 사용자가 시도함.");
        }
    }

    /**
     * 냉장고를 부탁해 시즌 2
     */
    @PostMapping("/menu-selections")
    public List<MenuDto> getMenuBySelections(@RequestBody Map<String,List<Integer>> icodeList_){
        List<Integer> icodeList = icodeList_.get("icodeList");
        return service.canMakeMenuThatLack2(icodeList);
    }
}
