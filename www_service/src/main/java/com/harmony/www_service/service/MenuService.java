package com.harmony.www_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.MenuListDao;
import com.harmony.www_service.dto.MenuDto;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    private MenuListDao menuListDao;

    public List<MenuDto> getMenuList(String category){
        
        return menuListDao.getMenuList(category);
    }
}
