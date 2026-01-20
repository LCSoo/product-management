package com.lcsoo.product_management.user;

import org.springframework.util.Assert;

public record DeleteUserRequest(String name, UserRole role) {

    public DeleteUserRequest {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(role, "역할은 필수입니다.");
    }
}
