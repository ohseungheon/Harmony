package com.harmony.www_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WholeSaleViewController {

	@GetMapping("/price_info/whole")
    public String wholeSale() {
		
        return "price_info/whole_sale";
    }
	
}
