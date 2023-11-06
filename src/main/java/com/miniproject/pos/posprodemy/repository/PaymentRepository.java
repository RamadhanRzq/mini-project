package com.miniproject.pos.posprodemy.repository;

import com.miniproject.pos.posprodemy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
