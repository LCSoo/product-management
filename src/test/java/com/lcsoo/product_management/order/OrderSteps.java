package com.lcsoo.product_management.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.List;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.lcsoo.product_management.domain.order.dto.OrderItemRequest;
import com.lcsoo.product_management.util.JsonUtil;

public class OrderSteps {

    public static OrderItemRequest 주문생성(UUID userId, List<String> productNames) {
        return new OrderItemRequest(userId, productNames);
    }

    public static ResultActions 주문생성요청(OrderItemRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.JSON(request)))
                .andDo(print());
    }

    public static ResultActions 주문유저생성요청(UUID userId, MockMvc mockMvc) throws Exception {
        return mockMvc.perform(
                    get("/orders")
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print());
    }
}
