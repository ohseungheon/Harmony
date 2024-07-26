package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/price_info")
public class PriceInfoController {
    
    @RequestMapping("/main")
    public String main(){
        return "price_info/food_price_chart";
    }
    
}
