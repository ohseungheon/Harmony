package com.harmony.www_service.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RecipeAllResponseDto {
	
	private List<RecipeDto> recipes;
	private Map<Integer, String> lastImgMap;

}
