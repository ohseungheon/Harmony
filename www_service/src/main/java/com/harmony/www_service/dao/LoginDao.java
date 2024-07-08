package com.harmony.www_service.dao;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.UserDto;

@Mapper
public interface LoginDao {

	public UserDto loginCheck(UserDto userDto);
	
}
