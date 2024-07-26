package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/price_info")
public class PriceInfoController {
    
    @RequestMapping("/main")
    public String main(){
        return "price_info/price_info_main";
    }
    
    @RequestMapping("/whole")
    public String whole(){
        return "price_info/whole_sale";
    }
    
    @RequestMapping("/retail")
    public String retail(){
        return "price_info/retail_sale";
    }
    
}
