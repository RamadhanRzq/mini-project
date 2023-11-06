package com.prodemy.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prodemy.miniproject.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);
	
}
