package com.lcsoo.product_management.user;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserApiTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Test
    void 유저생성요청_API() throws Exception {
        var request = UserSteps.유저생성("user");

        var response = UserSteps.유저생성요청(request, mockMvc);
             

        response
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$").exists());
    }

    @Test
    void 유저정보조회_API() throws Exception {
        UserSteps.유저생성요청(UserSteps.유저생성("user"), mockMvc);

        var response = UserSteps.유저정보조회요청("user", mockMvc);

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
    }

    @Test
    void 유저정보수정_API() throws Exception {
        
        UserSteps.유저생성요청(UserSteps.유저생성("user"), mockMvc);

        var request = UserSteps.유저수정정보생성("user");
        var response= UserSteps.유저정보수정요청(request, mockMvc);

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
    }

    @Test 
    void 유저정보삭제_API() throws Exception {

        UserSteps.유저생성요청(UserSteps.유저생성("user"), mockMvc);

        var request = UserSteps.유저삭제정보생성("user", UserRole.ADMIN);
        var response = UserSteps.유저정보삭제요청(request, mockMvc);

        response
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").exists());
    }

}
