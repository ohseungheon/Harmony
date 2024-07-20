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
    private List<Integer> icode;
    private List<Integer> amount;
    private List<Integer> orderNum;
    private List<String> orderContent;
    private List<MultipartFile> cookingImg;
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
        List<RecipeOrderDto> list = new ArrayList<>();
        for (int i = 0; i < orderNum.size(); i++) {
            RecipeOrderDto dto = new RecipeOrderDto();
            dto.setRcode(this.rcode);
            dto.setOrderNum(this.orderNum.get(i));
            dto.setOrderContent(this.orderContent.get(i));
            if (this.cookingImg != null && i < this.cookingImg.size()) {
                dto.setCookingImg(this.cookingImg.get(i).getOriginalFilename());
            }
            list.add(dto);
        }
        return list;
    }

    public RecipeTagDto toRecipeTagDto() {
        RecipeTagDto dto = new RecipeTagDto();
        dto.setRcode(this.rcode);
        dto.setTagContent(this.tagContent);
        return dto;
    }
	
}
