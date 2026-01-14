package com.lcsoo.product_management.product.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.product.domain.Product;

public record UpdatePriceResponse(
    Product product
) {
    public UpdatePriceResponse {
        Assert.notNull(product, "제품 정보는 필수입니다.");
    }
}
