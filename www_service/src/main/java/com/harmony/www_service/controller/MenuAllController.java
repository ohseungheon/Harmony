package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MenuAllController {
    
    @GetMapping("/menu_all/menu_all_list")
    public String menu_all_list(){

        return "menu_all/menu_all_list";
    }

    @GetMapping("/menu_all/recipe_list")
    public String recipe_list(@RequestParam("mcode") int mcode, Model model){

        model.addAttribute("mcode", mcode);
        return "menu_all/recipe_list";
    }



}
