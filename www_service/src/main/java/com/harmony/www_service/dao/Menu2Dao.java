package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.dto.MenuReqWithMember;

@Mapper
public interface Menu2Dao {
    public void insertMenuRequest(@Param("menuReq")MenuReqDto menuReq);
    public List<MenuReqWithMember> findAll();
}
