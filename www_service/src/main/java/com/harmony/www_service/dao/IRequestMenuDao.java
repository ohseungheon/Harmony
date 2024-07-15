package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.MenuReqDto;

@Mapper
public interface IRequestMenuDao {
	public List<MenuReqDto> findMenuRequest(@Param("mrcode") int mrcode);
	public void registRequestMenu(@Param("mrDto") MenuReqDto mrDto);
}
