package com.lcsoo.product_management.user;

import org.springframework.util.Assert;

public record AddUserResponse(User user) {
    public AddUserResponse {
        Assert.notNull(user, "유저 정보는 필수입니다.");
    }
}
