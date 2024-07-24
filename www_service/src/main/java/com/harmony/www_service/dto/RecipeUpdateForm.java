package com.harmony.www_service.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RecipeUpdateForm {

	private Integer mcode;
    private String recipeName;
    private String introduce;
    private String url;
    private String category;
    private int portions;
    private String theme;
    private List<Integer> icode;
    private List<Integer> amount;
    private List<Integer> orderNum;
    private List<String> orderContent;
    private List<MultipartFile> cookingImg;
    private List<String> existingCookingImg;
    private String tagContent;
    private int rcode;

    // getters and setters

    public RecipeDto toRecipeDto(int mno) {
        RecipeDto dto = new RecipeDto();
        dto.setMcode(this.mcode);
        dto.setRcode(this.rcode);
        dto.setMno(mno);
        dto.setRecipeName(this.recipeName);
        dto.setIntroduce(this.introduce);
        dto.setUrl(this.url);
        dto.setCategory(this.category);
        dto.setPortions(this.portions);
        dto.setTheme(this.theme);
        return dto;
    }

    public List<RecipeIngredientDto> toRecipeIngredientDtoList() {
        List<RecipeIngredientDto> list = new ArrayList<>();
        for (int i = 0; i < icode.size(); i++) {
            RecipeIngredientDto dto = new RecipeIngredientDto();
            dto.setRcode(this.rcode);
            dto.setIcode(this.icode.get(i));
            dto.setAmount(this.amount.get(i));
            list.add(dto);
        }
        return list;
    }

    public List<RecipeOrderDto> toRecipeOrderDtoList() {
        List<RecipeOrderDto> orderDtos = new ArrayList<>();
        for (int i = 0; i < orderContent.size(); i++) {
            RecipeOrderDto orderDto = new RecipeOrderDto();
            orderDto.setOrderNum(i + 1);
            orderDto.setOrderContent(orderContent.get(i));
            
            // 새 이미지가 업로드되었다면 그것을 사용, 아니면 기존 이미지 유지
            if (cookingImg != null && cookingImg.size() > i && !cookingImg.get(i).isEmpty()) {
                orderDto.setCookingImg(cookingImg.get(i).getOriginalFilename());
            } else if (existingCookingImg != null && existingCookingImg.size() > i) {
                orderDto.setCookingImg(existingCookingImg.get(i));
            }
            
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public RecipeTagDto toRecipeTagDto() {
        RecipeTagDto dto = new RecipeTagDto();
        dto.setRcode(this.rcode);
        dto.setTagContent(this.tagContent);
        return dto;
    }
	
}
