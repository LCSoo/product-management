package com.lcsoo.product_management.domain.user.dto;

import org.springframework.util.Assert;
import com.lcsoo.product_management.domain.user.domain.Address;

public record AddUserRequest(
    String email,
    String password,
    String name,
    Address address
) {
    public AddUserRequest {
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "비밀번호는 필수입니다.");
        Assert.hasText(name, "이름은 필수입니다.");
        Assert.notNull(address, "주소는 필수입니다.");
    }
}
