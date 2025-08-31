package com.example.ecommerceproject.user.service.impl;

import com.example.ecommerceproject.user.dto.UserLoginRequestDto;
import com.example.ecommerceproject.user.entity.User;
import com.example.ecommerceproject.user.repository.UserRepository;
import com.example.ecommerceproject.user.service.UserAuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserRepository userRepository;
    private final HttpSession session; // HttpSession 주입

    @Override
    public User login(UserLoginRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 이메일입니다."));

        if (!user.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 로그인 성공 시 세션에 사용자 정보를 저장합니다.
        session.setAttribute("loginUser", user);
        return user;
    }
}