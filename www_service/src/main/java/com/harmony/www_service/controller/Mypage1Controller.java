package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Mypage1Controller {
    
	@RequestMapping("/info_update")
	public String infoUpdate() {
		return "mypage1/info_update";
	}
}
