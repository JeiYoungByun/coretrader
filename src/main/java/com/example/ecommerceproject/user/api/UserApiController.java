package com.example.ecommerceproject.user.api;

import com.example.ecommerceproject.user.dto.UserLoginRequestDto;
import com.example.ecommerceproject.user.service.UserAuthService;
import com.example.ecommerceproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    private final UserAuthService userAuthService;

    @PostMapping("/api/user/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequestDto requestDto) {
        log.info("API 로그인 요청: {}", requestDto.getEmail());
        userAuthService.login(requestDto); // userAuthService 호출
        return ResponseEntity.ok("로그인 성공!");
    }
}