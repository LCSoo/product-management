package com.lcsoo.product_management.user;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import com.lcsoo.product_management.domain.user.domain.Address;
import com.lcsoo.product_management.domain.user.dto.AddUserRequest;
import com.lcsoo.product_management.domain.user.dto.DeleteUserRequest;
import com.lcsoo.product_management.domain.user.dto.UpdateUserRequest;
import com.lcsoo.product_management.type.UserRole;
import com.lcsoo.product_management.util.JsonUtil;

@AutoConfigureMockMvc
public class UserSteps {

    public static AddUserRequest 유저생성(String name) {
        Address address = new Address("city", "street", "zipCode");
        return new AddUserRequest("email", "password", name, address);
    }

    public static ResultActions 유저생성요청(AddUserRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.JSON(request))
            )
            .andDo(print());
    }

    public static ResultActions 유저정보조회요청(String username, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                get("/users/info")
                    .param("name", username)
            )
            .andDo(print());

    }

    public static UpdateUserRequest 유저수정정보생성(String username) {
       return new UpdateUserRequest(
            "update@email.com",
            "update_password",
            username,
            new Address("update_city", "update_street", "update_zipCode")
        ); 
    }

    public static ResultActions 유저정보수정요청(UpdateUserRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                patch("/users")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))
            )
            .andDo(print());
    }

    public static DeleteUserRequest 유저삭제정보생성(String username, UserRole admin) {
        return new DeleteUserRequest(username, admin);
    }

    public static ResultActions 유저정보삭제요청(DeleteUserRequest request, MockMvc mockMvc) throws Exception {
        return mockMvc
            .perform(
                delete("/users")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(JsonUtil.JSON(request))
            )
            .andDo(print());
    }
}
