package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@GetMapping("/searchList")
	@ResponseBody
	public List<ActDto> filterList(@RequestParam("query") String query){
		List<ActDto> act = actDao.getSearchList(query);
		System.out.println("searchList!!!!!!!!!!!!!"+act);
		return act;
	}
}
