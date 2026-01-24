package com.lcsoo.product_management.order;

import java.util.Map;
import org.springframework.util.Assert;

public record OrderResponse(Map<String, Object> data) {
    public OrderResponse {
        Assert.notNull(data, "data 값은 필수 입니다.");
    }
}
