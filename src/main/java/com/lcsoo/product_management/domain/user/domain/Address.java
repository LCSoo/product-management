package com.lcsoo.product_management.domain.user.domain;

import java.util.Map;
import java.util.Optional;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    private String city;
    private String street;
    private String zipCode;

    /* 메서드 */
    public Map<String, Object> toMap() {
        return Map.of(
            "city", city,
            "street", street,
            "zipCode", zipCode
        );
    }

    public void update(String city, String street, String zipCode) {
        Optional.ofNullable(city).ifPresent(c -> this.city = city);
        Optional.ofNullable(street).ifPresent(s -> this.street = street);
        Optional.ofNullable(zipCode).ifPresent(z -> this.zipCode = zipCode);
    }
}
