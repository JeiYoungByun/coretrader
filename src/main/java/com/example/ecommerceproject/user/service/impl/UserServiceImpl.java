package com.example.ecommerceproject.user.service.impl;

import com.example.ecommerceproject.user.dto.UserSignUpRequestDto;
import com.example.ecommerceproject.user.entity.User;
import com.example.ecommerceproject.user.repository.UserRepository;
import com.example.ecommerceproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional // 데이터 변경이 있으므로 트랜잭션 처리
    public User join(UserSignUpRequestDto requestDto) {
        // DTO를 Entity로 변환
        User user = requestDto.toEntity();

        // 이메일 중복 검사 (필요시)
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            log.warn("이메일 중복: {}", user.getEmail());
            // 예외를 던지거나 null을 반환하는 등의 처리 필요
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 암호화 (나중에 Spring Security 적용 시 구현)
        // String encodedPassword = passwordEncoder.encode(user.getPassword());
        // user.setPassword(encodedPassword);

        // Repository를 통해 DB에 저장
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다. id=" + id));
    }

}