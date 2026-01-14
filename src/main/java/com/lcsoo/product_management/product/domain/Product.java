package com.lcsoo.product_management.product.domain;

import org.springframework.util.Assert;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "products_name")
    private String name;

    @Column(name = "products_price")
    private Integer price;

    @Column(name = "products_amount")
    private Integer stock;

    public Product(String name, Integer price, Integer stock) {
        Assert.hasText(name, "제품명은 필수입니다.");
        Assert.isTrue(price > 0, "제품 가격은 0보다 커야 합니다.");
        Assert.isTrue(stock > 0, "제품 수량은 0보다 커야 합니다.");
    }

    public void addStock(Integer stock) {
        this.stock += stock;
    }

    public void removeStock(Integer stock) {
        Assert.isTrue(this.stock >= stock, "제품 수량은 0보다 커야 합니다.");
        this.stock -= stock;
    }
}
