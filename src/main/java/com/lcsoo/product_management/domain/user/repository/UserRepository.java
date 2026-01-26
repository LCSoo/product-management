package com.lcsoo.product_management.domain.user.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.lcsoo.product_management.domain.user.domain.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByName(String username);
}
