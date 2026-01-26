package com.lcsoo.product_management.domain.user.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.domain.user.domain.User;

public record AddUserResponse(User user) {
    public AddUserResponse {
        Assert.notNull(user, "유저 정보는 필수입니다.");
    }
}
