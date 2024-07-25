package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.harmony.www_service.dto.ActDto;

@Mapper
public interface IActDao {
	
	public List<ActDto> getActList();
}
