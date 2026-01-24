package com.lcsoo.product_management.order;

import java.util.List;
import java.util.UUID;
import org.springframework.util.Assert;

public record OrderItemRequest(UUID userId, List<String> productNames) {
    public OrderItemRequest {
        Assert.notNull(userId, "주문자는 필수입니다.");
        Assert.notNull(productNames, "주문 목록은 최소 1개 이상 입니다.");
    }
}
