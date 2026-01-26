package com.lcsoo.product_management.domain.order.domain;

import java.util.Map;
import org.springframework.util.Assert;
import com.lcsoo.product_management.domain.product.domain.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_id")
    private Product product;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    
    public void addStock(Integer stock) {
        Assert.isTrue(stock > 0, "수량은 최소 1개 이상이어야 합니다."); 
        this.stock += stock;
    }

    public void removeStock(Integer stock) {
        Assert.isTrue(stock > 0, "수량은 최소 1개 이상이어야 합니다.");
        this.stock -= stock;
    }

    public Map<String, Object> productDetails() {
        return Map.of(
            "제품명", this.product.getName(),
            "제품가격", this.product.getPrice(),
            "구매수량", this.stock,
            "구매가격", this.product.getPrice() * this.stock
        );
    }
}
