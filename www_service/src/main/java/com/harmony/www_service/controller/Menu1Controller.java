package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu1")

public class Menu1Controller {
    @RequestMapping("main")
	public String main() {
    	return "menu1/menu_harmony1";
    }
	
}
