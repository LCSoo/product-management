package com.lcsoo.product_management.domain.product.dto;

import org.springframework.util.Assert;

public record RemoveProductRequest(
    String productName
) {
    public RemoveProductRequest {
        Assert.hasText(productName, "제품명은 필수입니다.");
    }    
}
