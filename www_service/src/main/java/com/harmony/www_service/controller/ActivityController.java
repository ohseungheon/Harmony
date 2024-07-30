package com.harmony.www_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String main(){
        
    	return "act/main";
        // return "sub/ready_for_service";
    }
	
	@GetMapping("/getActiveList")
	@ResponseBody
	public List<ActDto> getActiveList(@RequestParam("page") int page, @RequestParam("itemsPerPage") int itemsPerPage){
		// page는 1부터 시작한다고 가정
	    int offset = (page - 1) * itemsPerPage;
		List<ActDto> list = actDao.getActList(offset, itemsPerPage);
		System.out.println(list+"지도!!!!!!!!!!!!!!!!!!!!!");
		return list;
	}
	
	@GetMapping("/searchList")
	@ResponseBody
	public List<ActDto> filterList(@RequestParam("query") String query){
		List<ActDto> act = actDao.getSearchList(query);
		System.out.println("searchList!!!!!!!!!!!!!"+act);
		return act;
	}
}
