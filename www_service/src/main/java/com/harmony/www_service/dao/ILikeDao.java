package com.harmony.www_service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.harmony.www_service.dto.Menu_favoriteDto_by;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.Recipe_recommendDto_by;

@Mapper
public interface ILikeDao {
	public List<Recipe_recommendDto_by> getRecipeLike(@Param("mno") int mno);

	public List<Menu_favoriteDto_by> getMenuLike(@Param("mno") int mno);

	public void deleteRecipeReco(@Param("rrcode")int rrcode);
	
	//메인 - 레시피 찜 3개만 가져오기
	public List<Recipe_recommendDto_by> getMainRecipeLike(int mno);
	//메인 - 나의 레시피 3개만 가져오기
	public List<RecipeDto> getMyRecipeList(int mno);
}
