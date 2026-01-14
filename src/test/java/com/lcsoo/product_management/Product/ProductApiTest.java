package com.lcsoo.product_management.Product;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.lcsoo.product_management.product.service.ProductService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ProductApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductService productService;

    @Test
    void 제품등록_API() throws Exception {

        var response = ProductSteps.제품등록요청(ProductSteps.제품생성("제품명"), mockMvc);

        response
            .andExpect(status().isCreated())
            .andExpect(jsonPath("name").value("제품명"))
            .andExpect(jsonPath("price").value(1000))
            .andExpect(jsonPath("amount").value(1000));

        System.out.println("[res] = "+response.andReturn().getResponse().getContentAsString());
    }

    @Test
    void 제품목록_조회_API() throws Exception {
        for(int i=0; i<5; i++) {
            ProductSteps.제품등록요청(ProductSteps.제품생성("제품"+i), mockMvc);
        }

        var response = ProductSteps.제품목록조회요청(mockMvc);
        
        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").exists())
            .andExpect(jsonPath("$[1]").exists())
            .andExpect(jsonPath("$[2]").exists())
            .andExpect(jsonPath("$[3]").exists())
            .andExpect(jsonPath("$[4]").exists());
    }

    @Test
    void 제품수량_수정_API() throws Exception {
        ProductSteps.제품등록요청(ProductSteps.제품생성("제품명"), mockMvc);

        var request = ProductSteps.제품수량수정_정보생성("제품명");
        var response = ProductSteps.제품수량수정요청(request, mockMvc);

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("제품명"))
            .andExpect(jsonPath("price").value(1000))
            .andExpect(jsonPath("stock").value(1100));
    }

    @Test
    void 제품정보_삭제_API() throws Exception {
        ProductSteps.제품등록요청(ProductSteps.제품생성("제품명"), mockMvc);
        var request = ProductSteps.제품삭제_정보생성("제품명");

        var response = ProductSteps.제품삭제요청(request, mockMvc);

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("삭제 완료"));
    }
}
