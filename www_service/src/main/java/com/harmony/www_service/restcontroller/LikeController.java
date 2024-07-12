package com.harmony.www_service.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.ILikeDao;

@RestController
@RequestMapping("/api")
public class LikeController {
	@Autowired
	ILikeDao likeDao;
	
	@DeleteMapping("/recipe-reco/{rrcode}")
	public String deleteRecipeReco( @PathVariable("rrcode") int rrcode) {
		System.out.println("삭제rrcode"+rrcode);
		
		likeDao.deleteRecipeReco( rrcode);
		
		return "해당 좋아요 레시피가 삭제되었습니다🙄";
	}
}
