package com.rapidcycling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rapidcycling.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);
}
