package com.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.data.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

