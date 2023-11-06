package com.prodemy.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.miniproject.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
    User findByEmail(String email);

}
