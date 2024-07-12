package com.harmony.www_service.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.harmony.www_service.dao.IIngredientDao;
import com.harmony.www_service.dto.IngredientDto;
import com.harmony.www_service.dto.IngredientDtoWithFile;

@Service
public class IngredientService_dally {

    @Value("${file.ingredients-dir}")
    private String ingredientsDir;
    
	@Autowired
	IIngredientDao iDao;
	
	public void addIngredients(IngredientDtoWithFile iDtoFile) {
		System.out.println("------service 진입-------");
		
		// 실제 DB에 저장하기 위한 IngredientDto 생성
		IngredientDto iDto = new IngredientDto();
		iDto.setName(iDtoFile.getName());
		iDto.setCategory(iDtoFile.getCategory());
		iDto.setTip(iDtoFile.getTip());
		iDto.setType(iDtoFile.getType());
		iDto.setImgurl(iDtoFile.getFileName());
		
		
		MultipartFile file = iDtoFile.getFile();
		String originName = "";
		String extension = "";
		if(file != null) {
			originName = iDtoFile.getFileName();
			extension = "";
		}
		
		System.out.println("----------------file : " + file );
		
		// 파일 확장자 추출
		int dotIndex = originName.lastIndexOf('.');
		if (dotIndex > 0 && dotIndex < originName.length() -1) {
			extension = originName.substring(dotIndex);
		}
		
		System.out.println("..........dotIndex : " + dotIndex);
		System.out.println("..........extension : " + extension);
		
		// 파일 이름 재구성 (고유한 이름으로 대체)
		String newName = UUID.randomUUID().toString() + extension;
		iDto.setImgurl(newName);
		iDao.registIngredient(iDto);
		
		System.out.println("................여기까지 동작.............");
		
		
		// 프로젝트 루트 경로를 기준으로 파일 저장 경로 설정
		try {
			Path projectRootPath = Paths.get("").toAbsolutePath().normalize();
			Path uploadPath = projectRootPath.resolve(ingredientsDir).normalize();
			Files.createDirectories(uploadPath);	// 업로드 디렉토리 생성
			
			// 파일 저장 경로 설정
			Path filePath = uploadPath.resolve(newName);
			
			// 파일을 지정된 경로에 저장
			iDtoFile.getFile().transferTo(filePath.toFile());
			
		} catch (IllegalStateException e) {
			System.err.println("MultipartFile 객체가 이미 사용된 상태에서 다시 transferTo 메소드를 호출하려고 할 때.\n"+ //);
					"파일 업로드 요청이 제대로 처리되지 않은 경우.\n " + //
					"MultipartFile 객체의 내부 상태가 적절하지 않은 경우.");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("파일을 디스크에 쓰는 도중 문제가 발생한 경우 (예: 디스크 공간 부족).\n" + //
					"파일 경로가 잘못되어 파일을 생성하거나 쓸 수 없는 경우.\n" + //
					"파일 시스템 접근 권한이 부족한 경우.\n" + //
					"파일 시스템에 접근하는 동안 발생한 다른 입출력 오류.");
			e.printStackTrace();
		}
		
	}
	
	// 재료 리스트 출력
	public List<IngredientDto> showList(String category){
		System.out.println("++++++++++++++++ 재료 리스트 출력 +++++++++++++++");
		List<IngredientDto> iList = iDao.findByCategory(category);
	
		// 화면에 표출할 수 있게 데이터 가공.....안해도 되나?
		//IngredientDtoWithFile iDtoFile =new IngredientDtoWithFile(); : 안해도됨
		
		return iList;
	}
	
	// 재료 디테일 출력
	public IngredientDto showDetail(int icode){
		System.out.println("++++++++++++++++ 재료 디테일 출력 +++++++++++++++");
		IngredientDto ingred_info = iDao.getDetail(icode);
	
		return ingred_info;
	}
	
	
	
	
	
	
	
}
