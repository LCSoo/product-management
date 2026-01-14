package com.lcsoo.product_management.product.dto;

import org.springframework.util.Assert;

public record UpdatePriceRequest(
    String productName,
    Integer price
) { 
    public UpdatePriceRequest {
        Assert.hasText(productName, "제품명은 필수입니다.");
        Assert.isTrue(price != 0, "최소 금액은 1원 이상입니다.");
    }
}
