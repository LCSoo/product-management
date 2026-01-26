package com.lcsoo.product_management.domain.order.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lcsoo.product_management.domain.order.domain.Order;
import com.lcsoo.product_management.domain.order.domain.OrderProduct;
import com.lcsoo.product_management.domain.order.dto.OrderItemRequest;
import com.lcsoo.product_management.domain.order.dto.OrderResponse;
import com.lcsoo.product_management.domain.order.repository.OrderRepository;
import com.lcsoo.product_management.domain.product.domain.Product;
import com.lcsoo.product_management.domain.product.repository.ProductRepository;
import com.lcsoo.product_management.domain.user.domain.Address;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    @Transactional
    public OrderResponse createOrder(OrderItemRequest request) {
        
        List<Product> products = productRepository.findByNameIn(request.productNames());
        Address deliveryAddress = new Address("delivery_city", "delivery_street", "delivery_zipcode");

        Order order = Order.create(deliveryAddress, request.userId());

        for (Product product : products) {
            OrderProduct orderProduct = OrderProduct.builder()
                    .order(order)
                    .product(product)
                    .stock(1)
                    .build();
            order.addOrderItem(orderProduct);
        }

        orderRepository.save(order);

        return new OrderResponse(Map.of(
            "message", "주문이 성공적으로 생성되었습니다.",
            "order_id", order.getId(),
            "user_id", order.getUserId(),
            "delivery_address", order.getDeliveryAddress()
        ));
    }


    @Transactional(readOnly = true)
    public OrderResponse getOrders(UUID userId) {
        
        List<Order> orders = orderRepository.findByUserId(userId);
        
        
        return new OrderResponse(Map.of(
            "message", "주문 조회 성공",
            "orders", orders.stream().map(Order::productList).collect(Collectors.toList())
        ));
    }


}
