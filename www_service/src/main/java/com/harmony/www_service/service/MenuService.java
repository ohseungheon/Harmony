package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dao.MenuListDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuListDao menuListDao;
    
    @Autowired
    private Menu1Dao menu1dao;
    

    public List<MenuDto> getMenuList(String category){
        
        return menuListDao.getMenuList(category);
    }
    
    public List<MenuDto> getCanMakeMenu(List<Integer> icodeList) {
    	
    	System.out.println("=============================getCanMakeMenu=======================");
    	System.out.println("=============================getCanMakeMenu icodeList======================= :"+icodeList);
    	int size = icodeList.size();
        return menu1dao.showCanMakeMenu(icodeList,size);
    }
    
    public List<Integer> makeIcodeList(List<IngredientDto> iList) {
    	
    	List<Integer> icodeList = new ArrayList<>();
    	for ( IngredientDto iListElement:iList) {
    		
    		int icode = iListElement.getIcode();
    		icodeList.add(icode);
    		
    		
    	}
    	
        return icodeList;
    }
    
}
