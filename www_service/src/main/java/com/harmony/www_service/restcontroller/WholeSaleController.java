package com.harmony.www_service.restcontroller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.harmony.www_service.dto.WholeSaleResponse;

@Controller
public class WholeSaleController {
	
	private final RestTemplate restTemplate;
	
	public WholeSaleController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@GetMapping("/price_info/whole")
	public String wholeSale(Model model) {
		
		String url = "https://www.kamis.or.kr/service/price/xml.do?action=periodProductList&p_cert_key=03fe98f4-cad4-43ad-91de-ee329f2512ee&p_cert_id=4625&p_returntype=json";
        ResponseEntity<WholeSaleResponse> response = restTemplate.getForEntity(url, WholeSaleResponse.class);
        WholeSaleResponse wholeSaleResponse = response.getBody();

        if (wholeSaleResponse != null && wholeSaleResponse.getData() != null) {
            model.addAttribute("wholeSaleList", wholeSaleResponse.getData());
        }
        
        return "price_info/whole_sale";
		
	}
	
}
