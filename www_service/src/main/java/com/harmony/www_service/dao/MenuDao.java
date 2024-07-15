package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MenuDto;

import java.util.List;

@Mapper
public interface MenuDao {
    List<MenuDto> getFilteredMenuList(@Param("categories") String categories, @Param("ingredients") String ingredients);
}