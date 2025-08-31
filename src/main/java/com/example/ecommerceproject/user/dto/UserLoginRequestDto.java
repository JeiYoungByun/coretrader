package com.example.ecommerceproject.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto { // 클래스명 변경
    private String email;
    private String password;
}