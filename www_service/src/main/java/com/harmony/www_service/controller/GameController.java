package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/game")
public class GameController {
    @RequestMapping("/main")
    public String main(){
        // return "game/main";
        return "sub/ready_for_service";
    }
}
