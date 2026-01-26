package com.lcsoo.product_management.domain.product.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.lcsoo.product_management.domain.product.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByNameIn(List<String> productNames);
}
