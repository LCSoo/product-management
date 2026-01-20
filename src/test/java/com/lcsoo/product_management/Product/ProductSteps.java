package com.lcsoo.product_management.product;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.lcsoo.product_management.product.dto.AddProductRequest;
import com.lcsoo.product_management.product.dto.RemoveProductRequest;
import com.lcsoo.product_management.product.dto.StockType;
import com.lcsoo.product_management.product.dto.UpdatePriceRequest;
import com.lcsoo.product_management.product.dto.UpdateStockRequest;
import com.lcsoo.product_management.util.JsonUtil;

@AutoConfigureMockMvc
public class ProductSteps {
    
    public static AddProductRequest 제품생성(String name) {
        return new AddProductRequest(name, 1000, 1000);
    }

    public static UpdateStockRequest 제품수량수정_정보생성(String name) {
        UpdateStockRequest request = new UpdateStockRequest(name, 100, StockType.ADD);
        return request;
    }

    public static UpdatePriceRequest 제품가격수정_정보생성(String name, Integer price) {
        return new UpdatePriceRequest(name, price);
    }

    public static RemoveProductRequest 제품삭제_정보생성(String name) {
        return new RemoveProductRequest(name);
    }
    
    public static ResultActions 제품등록요청(AddProductRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                post("/product")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))   
            )
            .andDo(print());
    }

    public static ResultActions 제품목록조회요청(MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                get("/product")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andDo(print());
    }


    public static ResultActions 제품수량수정요청(UpdateStockRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                patch("/product/update/stock")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))   
            )
            .andDo(print());
    }

    public static ResultActions 제품삭제요청(RemoveProductRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                delete("/product")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))   
            )
            .andDo(print());
    }

    public static ResultActions 제품가격수정요청(UpdatePriceRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                patch("/product/update/price")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))
            )
            .andDo(print());
    }

    
}