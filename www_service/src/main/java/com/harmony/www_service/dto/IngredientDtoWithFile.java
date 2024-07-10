package com.harmony.www_service.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class IngredientDtoWithFile {

	private int icode;
	private String name;
	private String type;
	private String tip;
	private String category;
	private MultipartFile file;
//    
//	public MultipartFile getFile() {
//		return file;
//	}
//	
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}
	
    public String getFileName() {
		return file.getOriginalFilename();
	}
	
}
