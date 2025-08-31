package com.example.ecommerceproject.user.service;

import com.example.ecommerceproject.user.dto.UserLoginRequestDto;
import com.example.ecommerceproject.user.entity.User;

public interface UserAuthService {
    User login(UserLoginRequestDto requestDto);
}