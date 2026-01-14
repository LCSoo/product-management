package com.lcsoo.product_management.product.dto;

import org.springframework.util.Assert;

public record UpdateStockRequest(
    String productName,
    Integer stock,
    StockType stockType
) {
    public UpdateStockRequest {
        Assert.hasText(productName, "제품명은 필수입니다.");
        Assert.isTrue(stock > 0, "제품 수량은 0보다 커야 합니다.");
        Assert.notNull(stockType, "stockType은 필수입니다.");
    }
}
