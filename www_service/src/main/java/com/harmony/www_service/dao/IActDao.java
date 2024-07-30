package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.ActDto;

@Mapper
public interface IActDao {
	
	public List<ActDto> getActList();
	
	//검색필터 리스트 
	public List<ActDto> getSearchList(@Param("query") String query);
}
