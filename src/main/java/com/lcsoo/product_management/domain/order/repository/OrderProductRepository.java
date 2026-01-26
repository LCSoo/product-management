package com.lcsoo.product_management.domain.order.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lcsoo.product_management.domain.order.domain.OrderProduct;
import com.lcsoo.product_management.domain.product.domain.Product;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

    List<Product> findByOrderId(Long orderId);
    
}
