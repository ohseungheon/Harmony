package com.harmony.www_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.ILikeDao;
import com.harmony.www_service.dto.Menu_favoriteDto_by;
import com.harmony.www_service.dto.Recipe_recommendDto_by;

@Service
public class LikeService {
	@Autowired
	ILikeDao likeDao;
	
	//추천한 레시피 리스트 가져오는 서비스
	public List<Recipe_recommendDto_by> getRecipeLike(int mno){
		return likeDao.getRecipeLike(mno);
	}
	//좋아요한 메뉴 리스트 가져오는 서비스
	public List<Menu_favoriteDto_by> getMenuLike(int mno){
		return likeDao.getMenuLike(mno);
	}
}
