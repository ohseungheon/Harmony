package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.dto.MenuReqWithMember;
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

        return "redirect:/menu2/"; 
    }
    
    @GetMapping("/")
    public String menuRequestTest(Model model){
        List<MenuReqWithMember> list = service.getAllMenuReqDto();
        model.addAttribute("mrList", list);
        
        return "menu2/menu_request_list";
    }
}
