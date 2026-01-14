package com.lcsoo.product_management.product.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.product.domain.Product;

public record UpdateStockResponse(Product product) {
    public UpdateStockResponse {
        Assert.notNull(product, "제품정보는 필수입니다.");
    }
}
