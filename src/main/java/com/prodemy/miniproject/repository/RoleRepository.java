package com.prodemy.miniproject.repository;

import com.prodemy.miniproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
