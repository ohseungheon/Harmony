package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class Recipe_recommendDto_by {
	private int rrcode; //추천코드
	private int mno; 
	private int rcode; //레시피코드
	
	private String recipeName;
}
