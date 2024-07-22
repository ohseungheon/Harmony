package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.MenuReqDto;

@Mapper
public interface IRequestMenuDao {
	public List<MenuReqDto> findMenuRequest();
	public MenuReqDto findById(@Param("mrcode") int mrcode);
	public boolean registRequestMenuToMenu(@Param("mDto") MenuDto mDto);
	public boolean deleteRequestMenu(@Param("mrcode") int mrcode);
	//public boolean addRequestMenuForAlarm(@Param("mrcode")int mrcode);
}
