package com.harmony.www_service.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.harmony.www_service.dao.MyRecipeDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.MenuDto;
import com.harmony.www_service.dto.RecipeDto;
import com.harmony.www_service.dto.RecipeIngredientDto;
import com.harmony.www_service.dto.RecipeOrderDto;
import com.harmony.www_service.dto.RecipeTagDto;
import com.harmony.www_service.dto.RecipeWithMenuDto;
import com.harmony.www_service.util.FileUploadUtil;

@Service
public class MyRecipeService {
	
	private static final Logger logger = LoggerFactory.getLogger(RecipeService.class);
	
	@Autowired
	private MyRecipeDao dao;
	
	// 나의 레시피 리스트
	public List<RecipeDto> myRecipeService(int mno){
		
		List<RecipeDto> list = dao.myRecipe(mno);
		
		return list;
	}
	
	public List<RecipeWithMenuDto> getRecipesWithMenu(int mno) {
	    List<RecipeDto> recipes = dao.myRecipe(mno);
	    List<RecipeWithMenuDto> result = new ArrayList<>();
	    
	    for (RecipeDto recipe : recipes) {
	        MenuDto menu = dao.recipeInMenu(recipe.getRcode());
	        result.add(new RecipeWithMenuDto(recipe, menu));
	    }
	    
	    return result;
	}
	
	// 나의 레시피 삭제
	public int deleteMyRecipe(int rcode) {
		
		int result = dao.deleteMyRecipe(rcode);
		
		return result;
	}
	
	// 레시피 등록
	public int registMyRecipeService(RecipeDto recipeDto) {
		
		int resultRecipe = dao.registMyRecipe(recipeDto);
		
		return resultRecipe;
	}

	// 레시피 재료 등록
	public int registMyRecipeIngredientService(RecipeIngredientDto recipeIngredientDto) {
		
		int resultRecipeIngredient = dao.registMyRecipeIngredient(recipeIngredientDto);
		
		return resultRecipeIngredient;
	}
	
	// 레시피 요리순서 등록
	public int registMyRecipeOrderService(RecipeOrderDto recipeOrderDto) {
		
		int resultRecipeOrder = dao.registMyRecipeOrder(recipeOrderDto);
		
		return resultRecipeOrder;
	}
	
	// 레시피 태그 등록
	public int registMyRecipeTagService(RecipeTagDto recipeTagDto) {
		
		int resultRecipeTag = dao.registMyRecipeTag(recipeTagDto);
		
		return resultRecipeTag;
	}
	
	@Transactional
    public void updateEntireRecipe(RecipeDto recipeDto, List<RecipeIngredientDto> ingredients, 
            List<RecipeOrderDto> orders, List<MultipartFile> newImages, 
            List<String> existingImages, RecipeTagDto recipeTagDto) {
        
        // 이미지 처리 로직
        for (int i = 0; i < orders.size(); i++) {
            RecipeOrderDto order = orders.get(i);
            if (newImages != null && newImages.size() > i && !newImages.get(i).isEmpty()) {
                // 새 이미지 처리
                try {
                    String fileName = FileUploadUtil.saveFile(newImages.get(i).getOriginalFilename(), newImages.get(i));
                    order.setCookingImg(fileName);
                } catch (IOException e) {
                    logger.error("Failed to upload file: ", e);
                    throw new RuntimeException("Failed to upload cooking image", e);
                }
            } else if (existingImages != null && existingImages.size() > i && !existingImages.get(i).isEmpty()) {
                // 기존 이미지 유지
                order.setCookingImg(existingImages.get(i));
            } else {
                // 이미지가 없는 경우
                order.setCookingImg(null);
            }
        }

        // 나머지 업데이트 수행
        updateMyRecipe(recipeDto);
        updateMyRecipeIngredients(ingredients, recipeDto.getRcode());
        updateMyRecipeOrders(orders, recipeDto.getRcode());
        updateMyRecipeTag(recipeTagDto);
    }
	
	
	// 레시피 수정 Service
	// 레시피 수정
	@Transactional
	public int updateMyRecipe(RecipeDto recipeDto) {
		
		logger.info("서비스 Updating recipe: {}", recipeDto);
        int result = dao.updateMyRecipe(recipeDto);
        if (result <= 0) {
            throw new RuntimeException("Failed to update recipe");
        }
        return result;
	}
	
	// 레시피 재료 수정
	@Transactional
	public int updateMyRecipeIngredients(List<RecipeIngredientDto> ingredients, int rcode) {
		
		logger.info("Updating recipe ingredients for rcode: {}", rcode);
        // 기존 재료 삭제
        dao.deleteAllRecipeIngredients(rcode);
        // 새로운 재료 추가
        for (RecipeIngredientDto ingredient : ingredients) {
            int result = dao.insertMyRecipeIngredient(ingredient);
            if (result <= 0) {
                throw new RuntimeException("Failed to insert new ingredient");
            }
        }
        return ingredients.size();
	}
	
	// 레시지 요리순서 수정
	@Transactional
    public int updateMyRecipeOrders(List<RecipeOrderDto> orders, int rcode) {
        logger.info("Updating recipe orders for rcode: {}", rcode);
        dao.deleteAllRecipeOrders(rcode);
        
        for (RecipeOrderDto order : orders) {
            order.setRcode(rcode);
            int result = dao.insertMyRecipeOrder(order);
            if (result <= 0) {
                throw new RuntimeException("Failed to insert new order");
            }
        }
        
        return orders.size();
    }
	
	// 레시피 태그 수정
	@Transactional
	public int updateMyRecipeTag(RecipeTagDto recipeTagDto) {
		
		logger.info("Updating recipe tag: {}", recipeTagDto);
        if (dao.existsRecipeTag(recipeTagDto.getRcode())) {
            int result = dao.updateMyRecipeTag(recipeTagDto);
            if (result <= 0) {
                throw new RuntimeException("Failed to update recipe tag");
            }
        } else {
            int result = dao.insertMyRecipeTag(recipeTagDto);
            if (result <= 0) {
                throw new RuntimeException("Failed to insert new recipe tag");
            }
        }
        return 1;
	}
	
	
	// 레시피 수정을 위한 데이터 보내기
	// 레시피 수정을 위한 레시피 정보
	public RecipeDto getRecipeService(int rcode) {
		
		return dao.getRecipeFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 재료 정보
	public List<RecipeIngredientDto> getRecipeIngredientService(int rcode){
		
		return dao.getRecipeIngredientFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 요리순서 정보
	public List<RecipeOrderDto> getRecipeOrderService(int rcode){
		
		return dao.getRecipeOrderFindByRcode(rcode);
	}
	
	// 레시피 수정을 위한 태그 정보
	public RecipeTagDto getRecipeTagService(int rcode) {
		
		return dao.getRecipeTagFindByRcode(rcode);
	}
	
	public int deleteMyRecipeIngredientService(int ricode) {
	    return dao.deleteMyRecipeIngredient(ricode);
	}
	
	public int deleteMyRecipeOrderService(int rocode) {
	    return dao.deleteMyRecipeOrder(rocode);
	}
	
	public List<IngredientDto> getIngredientService(){
		
		return dao.getIngredient();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
