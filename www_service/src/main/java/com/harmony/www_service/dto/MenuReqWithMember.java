package com.harmony.www_service.dto;

import lombok.Data;

@Data
public class MenuReqWithMember {
    private int mrcode;
    private String username;
    private String nickName;
    private String menuName;
    private String category;
    private String imgUrl;
}
