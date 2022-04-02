package com.example.ecommerce.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    private String userId;
    private String name;
    private String email;
    private String password;
    private String mobileNumber;

    @Builder
    public User(String userId, String name, String email, String password, String mobileNumber) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

}
