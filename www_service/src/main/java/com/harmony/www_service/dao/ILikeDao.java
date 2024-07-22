package com.harmony.www_service.dao;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.Menu_favoriteDto_by;
import com.harmony.www_service.dto.Recipe_recommendDto_by;

@Mapper
public interface ILikeDao {
	public List<Recipe_recommendDto_by> getRecipeLike(@Param("mno") int mno);

	public List<Menu_favoriteDto_by> getMenuLike(@Param("mno") int mno);

	public void deleteRecipeReco(@Param("rrcode") int rrcode);

	/**
	 * 레시피 찜(추천, 좋아요) 등록
	 * 
	 * @param mno   회원번호
	 * @param rcode 레시피 코드
	 */
	public void insertRecipeReco(@Param("mno") int mno, @Param("rcode") int rcode);

	/**
	 * 이미 레시피 찜 or 추천 or 좋아요 등록이 되어있는지 확인
	 * 이미 되어있을 경우 해당 데이터 rrcode 반환
	 */
	public Optional<Integer> isPresentRecommend(@Param("mno") int mno, @Param("rcode") int rcode);
}
