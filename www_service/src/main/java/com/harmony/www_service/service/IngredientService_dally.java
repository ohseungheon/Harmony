package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IIngredientDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDtoWithFile;

@Service
public class IngredientService_dally {

	@Autowired
	IIngredientDao iDao;
	
	public void save(IngredientDtoWithFile iDtoFile) {
		if(iDtoFile.getIcode() != -1) {
			iDao.registIngredient(iDtoFile);
		}
	}
	
	public List<IngredientDto> showList(String category){
		List<IngredientDto> iList = iDao.getList(category);
		
		return iList;
	}
}
