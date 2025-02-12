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
    
    
    @RequestMapping("/retail")
    public String retail(){
        return "price_info/retail_sale";
    }

    @RequestMapping("/retail-detail")
    public String retailDetail(){
        return "price_info/retail_sale_detail";
    }
    
}
