package com.harmony.www_service.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MenuRequestWithFile {
    String menu_name;
    String category;
    private MultipartFile file;
    
    public String getFileName() {
		return file.getOriginalFilename();
	}
}
