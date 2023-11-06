package com.miniproject.pos.posprodemy.repository;


import com.miniproject.pos.posprodemy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
