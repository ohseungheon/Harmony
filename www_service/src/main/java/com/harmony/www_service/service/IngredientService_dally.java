package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.IIngredientDao;
import com.harmony.www_service.dto.IngredientDto;

@Service
public class IngredientService_dally {

	@Autowired
	IIngredientDao iDao;
	
	public void save(IngredientDto iDto) {
		
		if(iDto.getIcode() != -1) {
			iDao.registIngredient(iDto);
		}
		
	}
}
