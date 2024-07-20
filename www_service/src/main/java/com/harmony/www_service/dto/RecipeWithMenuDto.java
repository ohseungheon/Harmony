package com.harmony.www_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeWithMenuDto {
	
	private RecipeDto recipe;
    private MenuDto menu;

}
