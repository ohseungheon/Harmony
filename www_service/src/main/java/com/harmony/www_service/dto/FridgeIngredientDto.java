package com.harmony.www_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FridgeIngredientDto {
	private int fcode; //냉장고재료코드
	private int mno;
	private int icode; //재료코드
	private int amount; //재료수량
	private LocalDate deadline; //유통기한
	private String memo; //비고란
	private String keeptype; //보관상태 
	
	private String name; //재료이름
	private String type; //단위구분
	private String tip;
	private String imgUrl;
	private String category; 
}
