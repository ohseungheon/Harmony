package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.MenuWithFile;


@Controller
@RequestMapping("/menu2")
public class Menu2Controller {

    
    @RequestMapping("/addForm")
    public String addRequestForm() {
        
        return "menu2/menu_request";
    }

    @PostMapping("/add")
    public String postMethodName(@RequestBody MenuWithFile reqMenu) {
        
        return "redirect:/"; // menu all list 로 이동시켜야 됨 
    }
    
}
