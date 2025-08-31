package com.example.ecommerceproject.user.dto;

import com.example.ecommerceproject.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserSignUpRequestDto {
    private String username; // 아이디
    private String password;
    private String email;
    private String name;     // '이름' 필드 추가

    public User toEntity() {
        // 추가된 name 필드를 Entity 생성자에 반영
        return new User(null, username, password, email, name, "USER");
    }
}