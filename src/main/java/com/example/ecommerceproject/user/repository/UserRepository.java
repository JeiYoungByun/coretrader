package com.example.ecommerceproject.user.repository;

import com.example.ecommerceproject.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 이메일로 사용자를 찾기 위한 메소드 추가
    Optional<User> findByEmail(String email);
}