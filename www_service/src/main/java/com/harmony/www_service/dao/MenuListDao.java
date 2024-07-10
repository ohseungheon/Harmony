package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.MenuDto;

@Mapper
public interface MenuListDao {
    List<MenuDto> getMenuListByCategory(String category);
}
