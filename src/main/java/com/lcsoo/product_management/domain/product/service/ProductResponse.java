package com.lcsoo.product_management.domain.product.service;

import java.util.List;
import org.springframework.util.Assert;
import com.lcsoo.product_management.domain.product.domain.Product;

public record ProductResponse(List<Product> products) {
    public ProductResponse {
        Assert.notNull(products, "제품 리스트는 필수입니다.");
    }
}
