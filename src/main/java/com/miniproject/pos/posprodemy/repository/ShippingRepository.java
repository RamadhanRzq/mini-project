package com.miniproject.pos.posprodemy.repository;

import com.miniproject.pos.posprodemy.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
