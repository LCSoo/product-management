package com.lcsoo.product_management.order;

import java.util.UUID;
import org.springframework.util.Assert;
import lombok.Builder;

@Builder
public record GetOrderRequest(UUID userId) {
    public GetOrderRequest {
        Assert.notNull(userId, "주문자 ID는 필수입니다.");
    }
}
