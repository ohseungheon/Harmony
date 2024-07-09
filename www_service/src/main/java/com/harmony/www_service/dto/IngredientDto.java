
package com.harmony.www_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
	

	private Integer icode;
	private String name;
	private Character type;
	private String tip;
	private String imgurl;
	private String cartegory;
	

}
