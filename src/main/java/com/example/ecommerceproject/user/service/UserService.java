package com.example.ecommerceproject.user.service;

import com.example.ecommerceproject.user.dto.UserSignUpRequestDto;
import com.example.ecommerceproject.user.entity.User;

public interface UserService {
    User join(UserSignUpRequestDto requestDto);
    User findById(Long id);
}