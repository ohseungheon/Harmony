package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harmony.www_service.dao.IActDao;
import com.harmony.www_service.dto.ActDto;

@Controller
@RequestMapping("/act")
public class ActivityController {
    @Autowired
    IActDao actDao;
	
	@RequestMapping("/main")
    public String main(Model model){
        List<ActDto> act = actDao.getActList();
        model.addAttribute("act", act);
    	return "act/main";
        // return "sub/ready_for_service";
    }
}
