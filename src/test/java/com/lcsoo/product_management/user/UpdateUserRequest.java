package com.lcsoo.product_management.user;

import org.springframework.util.Assert;

public record UpdateUserRequest(
    String email,
    String password,
    String name,
    Address address
) {
    public UpdateUserRequest {
        Assert.notNull(email, "이메일은 필수입니다.");
        Assert.notNull(password, "패스워드는 필수입니다.");
        Assert.notNull(name, "이름은 필수입니다.");
        Assert.notNull(address, "주소는 필수입니다.");
    }
}
