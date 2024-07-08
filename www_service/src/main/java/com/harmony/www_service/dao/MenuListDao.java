package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.MenuDto;

import java.util.List;

@Mapper
public interface MenuListDao {
    List<MenuDto> getMenuList(String category);
}
