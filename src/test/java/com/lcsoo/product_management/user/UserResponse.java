package com.lcsoo.product_management.user;

import java.util.Map;
import org.springframework.util.Assert;

public record UserResponse(
    Map<String, Object> data
) {
    public UserResponse {
        Assert.notNull(data, "데이터값이 필수입니다.");
    }
}
