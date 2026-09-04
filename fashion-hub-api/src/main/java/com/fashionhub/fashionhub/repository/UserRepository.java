package com.fashionhub.fashionhub.repository;

import com.fashionhub.fashionhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameAndIdNot(String username, Long id);
    boolean existsByEmailAndIdNot(String email, Long id);
}
