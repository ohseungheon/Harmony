package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/music")
public class MusicController {
    
    @RequestMapping("/main")
    public String musicMain() {
        // return "music/main";
        return "sub/ready_for_service";
    }
    
    
}
