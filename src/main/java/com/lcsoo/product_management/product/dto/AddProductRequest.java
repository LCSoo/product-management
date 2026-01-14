package com.lcsoo.product_management.product.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.product.domain.Product;

public record AddProductRequest(
    String name,
    Integer price,
    Integer stock
) {
    public AddProductRequest {
        Assert.hasText(name, "제품명은 필수입니다.");
        Assert.isTrue(price > 0, "제품 가격은 0보다 커야 합니다.");
        Assert.isTrue(stock > 0, "제품 수량은 0보다 커야 합니다.");
    }

    public Product toProduct() {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .stock(this.stock)
                .build();
    }
}
