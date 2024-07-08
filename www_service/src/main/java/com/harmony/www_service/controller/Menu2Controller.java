package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu2")
public class Menu2Controller {

    
    @RequestMapping("/addForm")
    public String addRequestForm() {

        
        return "menu2/menu_request";
    }
}
