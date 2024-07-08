package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuAllController {
    
    @GetMapping("/menu_all/menu_all_list")
    public String menu_all_list(){

        return "menu_all/menu_all_list";
    }
}
