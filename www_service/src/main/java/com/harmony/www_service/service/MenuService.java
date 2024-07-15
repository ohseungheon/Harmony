package com.harmony.www_service.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import com.harmony.www_service.dao.Menu1Dao;
import com.harmony.www_service.dao.MenuDao;
import com.harmony.www_service.dao.MenuListDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;

@Service
public class MenuService {
    
    @Autowired
    private Menu1Dao menu1dao;
    @Autowired
    private MenuListDao menuListDao;
    @Autowired
    private MenuDao menuDao;
    
//    public List<MenuDto> getMenuList(String category){
//        
//        return menuListDao.getMenuList(category);
//    }
    
    
    public IngredientDto getOneIngredient(int icode) {
       //System.out.println("=============================getOneIngredient======================= :"+menu1dao.showOneIngredient(icode));
       return menu1dao.showOneIngredient(icode);
       
    }

    public List<MenuDto> getCanMakeMenu(List<IngredientDto> iList) {
       List<Integer> icodeList = makeIcodeList(iList);
       //System.out.println("=============================getCanMakeMenu=======================");
       //System.out.println("=============================getCanMakeMenu icodeList======================= :"+icodeList);
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
    
    public List<Integer> makeMcodeList(List<MenuDto> menuDtoList){
       List<Integer> mcodeList =  new ArrayList<>();
       
       for ( MenuDto menuListElement:menuDtoList) {
          
         int mcode = menuListElement.getMcode();
         mcodeList.add(mcode);
      
      
      }
       return mcodeList; 
    }
    
    public List<IngredientDto> selectExcludeIngredient(List<IngredientDto> iList,int mno){
       List<Integer> icodeList = makeIcodeList(iList);
       return menu1dao.selectExcludeIngredient(icodeList,mno);
    }
    public int getMno(String username) {
       return  menu1dao.getMno(username);
    }
   
//    public List<Integer> mcodeListFromIcodeList(List<IngredientDto> iList) {
//       List<MenuDto> menuDtoList = getCanMakeMenu(iList);
//       List<Integer> mcodeList
//       for ()
//       
//       
//       return  ;
//    }
    
    
    @ResponseBody
    public List<Integer> getRcodeForMcode(int mcode){
       List<Integer> rcodeList= menu1dao.getRcodeForMcode(mcode);
       return rcodeList;
    }
    
    public List<MenuDto> getFilteredMenuList(String categories, String ingredients) {
        if (categories == null) categories = "";
        if (ingredients == null) ingredients = "";
        return menuDao.getFilteredMenuList(categories, ingredients);
    }
}
