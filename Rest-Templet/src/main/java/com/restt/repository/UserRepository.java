package com.restt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restt.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom queries here if needed
}
