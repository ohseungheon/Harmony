package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.IMypage1Dao;

@RestController
@CrossOrigin("*")
@RequestMapping("/menu2-api")
public class Menu2RestCon {
    
    @Autowired
    IMypage1Dao myIngredientDao;
    
    // @GetMapping("/my-ingredients")
    public void a(){

        myIngredientDao.getAllFoodList(2);
    }
    

}
