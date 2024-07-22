package com.harmony.www_service.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FavorMenuDao {
    
    /**
     * 삭제
     */
    public void deleteFavorMenu(@Param("fmcode") int fmcode);

    /**
     * 메뉴 좋아요 등록
     * 
     * @param mno   회원번호
     * @param mcode 메뉴 코드
     */
    public void insertFavorMenu(@Param("mno") int mno, @Param("mcode") int mcode);

    /**
     * 이미 메뉴 좋아요 등록이 되어있는지 확인
     * 이미 되어있을 경우 해당 데이터 fmcode 반환
     */
    public Optional<Integer> isPresentFavor(@Param("mno") int mno, @Param("mcode") int mcode);
}
