package com.lcsoo.product_management.order;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.lcsoo.product_management.domain.order.service.OrderService;
import com.lcsoo.product_management.domain.product.repository.ProductRepository;
import com.lcsoo.product_management.domain.user.domain.User;
import com.lcsoo.product_management.domain.user.repository.UserRepository;
import com.lcsoo.product_management.product.ProductSteps;
import com.lcsoo.product_management.user.UserSteps;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderApiTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    ProductRepository productRepository;

    @Test
    void 주문목록생성_API() throws Exception {
        UserSteps.유저생성요청(UserSteps.유저생성("name"), mockMvc);
        ProductSteps.제품등록요청(ProductSteps.제품생성("product1"), mockMvc);
        ProductSteps.제품등록요청(ProductSteps.제품생성("product2"), mockMvc);
        
        User order_writer = userRepository.findByName("name").get();
        List<String> products = List.of("product1", "product2");

        var request = OrderSteps.주문생성(order_writer.getId(), products);
        var response = OrderSteps.주문생성요청(request, mockMvc);
    
        response
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.data.message").value("주문이 성공적으로 생성되었습니다."));

        System.out.println("[p1] = "+ productRepository.findByName("product1").get());
        System.out.println("[p2] = "+ productRepository.findByName("product2").get());
    }

    @Test
    void 유저주문조회_API() throws Exception {
        UserSteps.유저생성요청(UserSteps.유저생성("name"), mockMvc);
        ProductSteps.제품등록요청(ProductSteps.제품생성("product1"), mockMvc);
        ProductSteps.제품등록요청(ProductSteps.제품생성("product2"), mockMvc);

        User order_writer = userRepository.findByName("name").get();
        List<String> op1 = List.of("product1", "product2");
        List<String> op2 = List.of("product1");

        OrderSteps.주문생성요청(OrderSteps.주문생성(order_writer.getId(), op1), mockMvc);
        OrderSteps.주문생성요청(OrderSteps.주문생성(order_writer.getId(), op2), mockMvc);

        var response = OrderSteps.주문유저생성요청(order_writer.getId(), mockMvc);
    

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data.message").value("주문 조회 성공"));
    }
}
