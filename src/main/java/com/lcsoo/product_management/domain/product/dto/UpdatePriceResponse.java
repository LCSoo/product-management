package com.lcsoo.product_management.domain.product.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.domain.product.domain.Product;

public record UpdatePriceResponse(
    Product product
) {
    public UpdatePriceResponse {
        Assert.notNull(product, "제품 정보는 필수입니다.");
    }
}
