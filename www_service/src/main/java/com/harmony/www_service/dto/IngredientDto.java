
package com.harmony.www_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
	
	private int icode;
	private String name;
	private String tip;
	private String imgurl;
	private String category;

}
