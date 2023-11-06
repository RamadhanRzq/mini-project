package com.prodemy.miniproject.repository;

import com.prodemy.miniproject.model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
}
