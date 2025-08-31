package com.example.ecommerceproject.user.controller;

import com.example.ecommerceproject.user.dto.UserSignUpRequestDto;
import com.example.ecommerceproject.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        log.info("GET /user/login - 로그인 페이지 요청");
        return "user/login";
    }

    @GetMapping("/signUp")
    public String signUpPage() {
        log.info("GET /user/signUp - 회원가입 페이지 요청");
        return "user/signUp";
    }

    @PostMapping("/join")
    public String join(UserSignUpRequestDto requestDto) {
        log.info("POST /user/join - 회원가입 폼 제출 DTO: {}", requestDto);
        userService.join(requestDto);
        return "redirect:/user/login?success";
    }

    // 로그아웃 기능
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        log.info("GET /user/logout - 로그아웃 요청");
        session.invalidate(); // 세션 무효화
        return "redirect:/main/mainPage"; // 메인 페이지로 리다이렉트
    }

}