
package com.harmony.www_service.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto2 {
	
	private int icode;
	private int mno;
	private String name;
	private String type;
	private String tip;
	private String imgurl;
	private String category;
	private LocalDate deadline; //유통기한

}
