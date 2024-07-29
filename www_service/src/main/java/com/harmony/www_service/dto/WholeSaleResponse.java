package com.harmony.www_service.dto;

import java.util.List;

import lombok.Data;

@Data
public class WholeSaleResponse {
	
	private List<WholeSaleDto> data;
	
	@Data
	public static class WholeSaleItem{
		private String productName;
		private String price;
	}

	
}
