package com.lcsoo.product_management.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.util.Assert;
import com.lcsoo.product_management.user.domain.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "users_id")
    private UUID userId;
    
    @Builder.Default
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> orderItem = new ArrayList<>();

    @Embedded
    private Address deliveryAddress;

    /* 생성자 */
    public Order(UUID userId, List<OrderProduct> orderItem, Address deliveryAddress) {
        Assert.notNull(userId, "사용자는 필수입니다.");
        Assert.notEmpty(orderItem, "주문 목록은 필수입니다.");
        Assert.notNull(deliveryAddress, "배송지는 필수입니다.");
        this.userId = userId;
        this.orderItem = orderItem;
        this.deliveryAddress = deliveryAddress;
    }

    /* 메서드 */
    public static Order create(Address delivery, UUID userId) {
        return Order.builder()
                .userId(userId)
                .deliveryAddress(delivery)
                .build();
    }

    public void addOrderItem(OrderProduct orderProduct) {
        Assert.notNull(orderProduct, "상품은 필수입니다.");
        this.orderItem.add(orderProduct);
        orderProduct.setOrder(this);
        orderProduct.getProduct().removeStock(orderProduct.getStock());
    }

    public void removeOrderItem(OrderProduct orderProduct) {
        Assert.notNull(orderProduct, "상품은 필수입니다.");
        this.orderItem.remove(orderProduct);
        orderProduct.setOrder(null);
        orderProduct.getProduct().addStock(orderProduct.getStock());
    }

    public Map<String, Object> productList() {
        return Map.of(
            "주문ID", this.id,
            "배송지", this.deliveryAddress,
            "구매상품", this.orderItem.stream().collect(Collectors.toMap(
                op -> op.getProduct().getName(),
                op -> op.productDetails()
            ))
        );
    }
}
