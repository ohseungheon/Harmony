package com.harmony.www_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dao.MenuListDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

@Service
public class MenuService {
    
    @Autowired
    private Menu1Dao menu1dao;
    @Autowired
    private MenuListDao menuListDao;
    
//    public List<MenuDto> getMenuList(String category){
//        
//        return menuListDao.getMenuList(category);
//    }
    
    
    public IngredientDto getOneIngredient(int icode) {
    	System.out.println("=============================getOneIngredient======================= :"+menu1dao.showOneIngredient(icode));
    	return menu1dao.showOneIngredient(icode);
    	
    }

    public List<MenuDto> getCanMakeMenu(List<IngredientDto> iList) {
    	List<Integer> icodeList = makeIcodeList(iList);
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
    
    public List<IngredientDto> selectExcludeIngredient(List<IngredientDto> iList,int mno){
    	List<Integer> icodeList = makeIcodeList(iList);
    	return menu1dao.selectExcludeIngredient(icodeList,mno);
    }
    public int getMno(String username) {
    	return  menu1dao.getMno(username);
    }
    
    
    @ResponseBody
    public List<Integer> getRcodeForMcode(int mcode){
    	List<Integer> rcodeList= menu1dao.getRcodeForMcode(mcode);
    	return rcodeList;
    }
    
}
