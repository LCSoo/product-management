package com.lcsoo.product_management.domain.user.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.type.UserRole;

public record DeleteUserRequest(String name, UserRole role) {

    public DeleteUserRequest {
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(role, "역할은 필수입니다.");
    }
}
