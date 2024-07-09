package com.harmony.www_service.service;

import java.util.List;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.harmony.www_service.dao.Menu2Dao;
import com.harmony.www_service.dto.MenuReqDto;
import com.harmony.www_service.dto.MenuReqWithMember;
import com.harmony.www_service.dto.MenuRequestWithFile;

@Service
public class Menu2Service {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    private Menu2Dao dao;

    public void addMenuRequest(MenuRequestWithFile reqMenu, int mno) {

        // 실제로 DB에 저장하기 위한 menuReqDto 객체 생성
        MenuReqDto menuReqDto = new MenuReqDto();
        menuReqDto.setMno(mno);
        menuReqDto.setMenuName(reqMenu.getMenu_name());
        menuReqDto.setCategory(reqMenu.getCategory());

        String originName = reqMenu.getFileName();
        String extension = "";

        // 파일 확장자 추출
        int dotIndex = originName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originName.length() - 1) {
            extension = originName.substring(dotIndex);
        }

        // 파일 이름 재구성 ( 고유한 이름으로 대체 )
        String newName = UUID.randomUUID().toString() + extension;
        menuReqDto.setImgUrl(newName);

        dao.insertMenuRequest(menuReqDto);

        try {
            // 프로젝트 루트 경로를 기준으로 파일 저장 경로 설정
            Path projectRootPath = Paths.get("").toAbsolutePath().normalize();
            Path uploadPath = projectRootPath.resolve(uploadDir).normalize();
            Files.createDirectories(uploadPath); // 업로드 디렉토리 생성

            // 파일 저장 경로 설정
            Path filePath = uploadPath.resolve(newName);

            // 파일을 지정된 경로에 저장
            reqMenu.getFile().transferTo(filePath.toFile());

        } catch (IllegalStateException e) {
            System.err.println("MultipartFile 객체가 이미 사용된 상태에서 다시 transferTo 메소드를 호출하려고 할 때.\n" + //
                    "파일 업로드 요청이 제대로 처리되지 않은 경우.\n" + //
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

    public List<MenuReqWithMember> getAllMenuReqDto() {

        return dao.findAll();
    }

}
