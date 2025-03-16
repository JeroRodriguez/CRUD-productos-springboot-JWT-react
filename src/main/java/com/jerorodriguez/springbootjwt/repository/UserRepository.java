package com.jerorodriguez.springbootjwt.repository;

import com.jerorodriguez.springbootjwt.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
