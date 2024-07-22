package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MenuReqDto;

@Mapper
public interface IRequestMenuDao {
	public List<MenuReqDto> findMenuRequest();
	public boolean registRequestMenuToMenu(@Param("mrDto") MenuReqDto mrDto);
	public boolean deleteRequestMenu(@Param("mrcode") int mrcode);
	//public boolean addRequestMenuForAlarm(@Param("mrcode")int mrcode);
}
