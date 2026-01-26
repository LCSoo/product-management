package com.lcsoo.product_management.domain.user.domain;

import java.util.Optional;
import java.util.UUID;
import org.springframework.util.Assert;
import com.lcsoo.product_management.type.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Embedded
    private Address address;




    /* 생성자 */
    public User(String email, String password, String name, Address address) {
        Assert.hasText(email, "이메일은 필수입니다.");
        Assert.hasText(password, "패스워드는 필수입니다.");
        Assert.hasText(name, "이름은 필수입니다.");
        Assert.notNull(address, "주소는 필수입니다.");

        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
    }

    /* 메서드 */
    public void update(String email, String password, String name, Address address) {
        Optional.ofNullable(email).ifPresent(e -> this.email = email);
        Optional.ofNullable(password).ifPresent(p -> this.password = password);
        Optional.ofNullable(name).ifPresent(n -> this.name = name);
        Optional.ofNullable(address).ifPresent(a -> this.address.update(a.getCity(), a.getStreet(), a.getZipCode()));
    }
}
