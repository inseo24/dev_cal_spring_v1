package com.example.ecommerce.mapper;

import com.example.ecommerce.domain.User;
import com.example.ecommerce.persistence.user.UserJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserJpaEntity mapToJpaEntity(User user) {
        return UserJpaEntity.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .mobileNumber(user.getMobileNumber())
                .name(user.getName())
                .build();
    }

    public User mapToDomain(UserJpaEntity entity) {
        return User.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .mobileNumber(entity.getMobileNumber())
                .build();
    }

}
