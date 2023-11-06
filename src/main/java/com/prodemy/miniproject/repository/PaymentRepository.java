package com.prodemy.miniproject.repository;

import com.prodemy.miniproject.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
