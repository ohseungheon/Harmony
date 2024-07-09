package com.harmony.www_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.MenuRequestWithFile;
import com.harmony.www_service.service.Menu2Service;


@Controller
@RequestMapping("/menu2")
public class Menu2Controller {

    @Autowired
    Menu2Service service;
    
    @RequestMapping("/addForm")
    public String addRequestForm() {
        
        return "menu2/menu_request";
    }

    @PostMapping("/add")
    public String postMethodName( MenuRequestWithFile reqMenu) {
        
        service.addMenuRequest(reqMenu); 

        return "redirect:/menu_all/menu_all_list"; 
    }
}
