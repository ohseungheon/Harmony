package com.harmony.www_service.restcontroller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@CrossOrigin("*")
@RestController
public class WholeSaleController {
	
	private final RestTemplate restTemplate;
	
	public WholeSaleController(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	String apiKey = "03fe98f4-cad4-43ad-91de-ee329f2512ee";
	String id = "4625";
	
	
	@GetMapping("/api/wholesale")
    public ResponseEntity<String> getWholeSaleData() {
		String url = "https://www.kamis.or.kr/service/price/xml.do" +
		        "?action=dailyPriceByCategoryList" +
		        "&p_product_cls_code=02" +
		        "&p_regday=2024-07-26" +  // 오늘 날짜
		        "&p_convert_kg_yn=N" +
		        "&p_item_category_code=100" +  // 채소류
		        "&p_cert_key=" + apiKey +
		        "&p_cert_id=" + id +
		        "&p_returntype=json";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("API Response: " + response.getBody());
        return response;
    }
	
	private String getCurrentDate() {
	    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
	
}
