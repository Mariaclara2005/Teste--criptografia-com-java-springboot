package com.example.authdemo.repository;

import com.example.authdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositorio extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
