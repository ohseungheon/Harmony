package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDto2;
import com.harmony.www_service.dto.MenuDto;

@Mapper
public interface Menu1Dao {
	List<IngredientDto> showFridgeIngredient(@Param("mno") int mno);
	List<IngredientDto2> showFridgeIngredientForDto(@Param("mno") int mno);
	IngredientDto showOneIngredient(@Param("icode") int icode);
	//MenuDto showCanMakeMenu();
    List<MenuDto> showCanMakeMenu(@Param("icodeList") List<Integer> icodeList, @Param("size") Integer size);
    List<MenuDto> showCanMakeMenu2(@Param("icodeList") List<Integer> icodeList, @Param("size") Integer size);
    List<MenuDto> showCanMakeMenu2_exclude(@Param("icodeList") List<Integer> icodeList,@Param("excludList") List<Integer> excludList );
    List<IngredientDto> selectExcludeIngredient(@Param("icodeList") List<Integer> icodeList,@Param("mno") int mno);
    int getMno(@Param("username") String username);
    
    
   // int countIntersection(@Param("fridgeIngredientList") List<Integer> fridgeIngredientList ,@Param("icodeList") List<Integer> recipeIcodeList);
    
    List<Integer> showFridgeIngredientIcodeList(@Param("mno") int mno);
    List<Integer> showRecipeIngredientIcodeList(@Param("rcode") int rcode);
    List<Integer> getRcodeForMcode(int rcode);
    
    List<Integer>  getCountUsedIcodeFromInfridgeIcodeList(@Param("icodeList") List<Integer> icodeList);
    List<Integer>  getCountUsedIcodeFromInfridgeIcodeList2(@Param("icodeList") List<Integer> icodeList,@Param("excludList") List<Integer> excludList);
    
    
}
