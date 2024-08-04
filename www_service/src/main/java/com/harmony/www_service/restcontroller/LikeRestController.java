package com.harmony.www_service.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.harmony.www_service.dao.FavorMenuDao;
import com.harmony.www_service.dao.ILikeDao;
import com.harmony.www_service.service.MemberService;

@RestController
@RequestMapping("/api")
public class LikeRestController {
	@Autowired
	ILikeDao likeDao;

	@Autowired
	MemberService memberService;

	@Autowired
	FavorMenuDao favoritesDao;

	@DeleteMapping("/recipe-reco/{rrcode}")
	public String deleteRecipeReco(@PathVariable("rrcode") int rrcode) {
		System.out.println("삭제rrcode" + rrcode);

		likeDao.deleteRecipeReco(rrcode);

		return "해당 레시피 추천을 취소하였습니다🙄";
	}

	// 레시피 추천(좋아요, 추천) 등록 레시피 코드 필요
	@GetMapping("/recipe-reco/{rcode}")
	public String insertRecommend(@PathVariable("rcode") int rcode) {
		String msg = "비정상적인 작동";

		// security 내부의 로그인 되어 있는 아이디( username ) 불러오기
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// username으로 회원 정보 테이블의 mno 불러오기
		Optional<Integer> mno_ = memberService.getMnoByUsername(username);
		if (mno_.isPresent()) { // 로그인된 아이디로 회원 정보가 존재하면
			int mno = mno_.get();
			Optional<Integer> rrcodeOptional = likeDao.isPresentRecommend(mno, rcode);
			if (rrcodeOptional.isPresent()) { // 이미 추천 해놨다면
				int rrcode = rrcodeOptional.get();
				likeDao.deleteRecipeReco(rrcode);
				msg = "해당 레시피 추천을 취소하였습니다🙄";
			} else {
				likeDao.insertRecipeReco(mno, rcode);
				msg = "레시피 추천 성공👍";
			}

		} else {
			// 로그인된 아이디의 회원 정보가 없으면 로그인 페이지로
			msg = "추천하기 실패 😢 로그인 요망";

		}

		return msg;
	}


	// 메뉴 좋아요 등록, 메뉴 코드 필요
	@GetMapping("/favor-menu/{mcode}")
	public String insertFavorMenu(@PathVariable("mcode") int mcode) {
		String msg = "비정상적인 작동";

		// security 내부의 로그인 되어 있는 아이디( username ) 불러오기
		String username = SecurityContextHolder.getContext().getAuthentication().getName();

		// username으로 회원 정보 테이블의 mno 불러오기
		Optional<Integer> mno_ = memberService.getMnoByUsername(username);
		if (mno_.isPresent()) { // 로그인된 아이디로 회원 정보가 존재하면
			int mno = mno_.get();
			Optional<Integer> fmcodeOptional = favoritesDao.isPresentFavor(mno, mcode);
			if (fmcodeOptional.isPresent()) { // 이미 좋아요 해놨다면
				int fmcode = fmcodeOptional.get();
				favoritesDao.deleteFavorMenu(fmcode);
				msg = "해당 메뉴 좋아요를 취소하였습니다🙄";
			} else {
				favoritesDao.insertFavorMenu(mno, mcode);
				msg = "메뉴 좋아요 성공👍";
			}

		} else {
			// 로그인된 아이디의 회원 정보가 없으면 응답 메세지
			msg = "좋아요 등록 실패 😢 로그인 요망";

		}

		return msg;
	}

	@GetMapping("/check-recipe-liked/{rcode}")
	public ResponseEntity<Boolean> checkIfRecipeLiked(@PathVariable("rcode") int rcode) {
		// security 내부의 로그인 되어 있는 아이디( username ) 불러오기
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		// username으로 회원 정보 테이블의 mno 불러오기
		Optional<Integer> mno_ = memberService.getMnoByUsername(username);
		if (mno_.isPresent()) {
			int mno = mno_.get();
			Optional<Integer> rrcodeOptional = likeDao.isPresentRecommend(mno, rcode);
			if (rrcodeOptional.isPresent()) {
				return ResponseEntity.ok(true);
			}
		}
		return ResponseEntity.ok(false);
	}
}
