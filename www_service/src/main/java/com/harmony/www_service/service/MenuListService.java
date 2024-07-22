package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.MenuListDao;
import com.harmony.www_service.dto.MenuDto;

import java.util.List;

@Service
public class MenuListService {
    @Autowired
    private MenuListDao menuListDao;

    public List<MenuDto> getMenuListByCategory(String category){
        
        return menuListDao.getMenuListByCategory(category);
    }

    public List<MenuDto> getAllMenus(){

        return menuListDao.getAllMenus();
    }
}
