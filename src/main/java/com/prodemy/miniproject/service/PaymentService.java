package com.prodemy.miniproject.service;

import com.prodemy.miniproject.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    public List<Payment> getAllPayment();
    public void addPayment(Payment payment);
    public void removePaymentById(Long id);
    public Optional<Payment> getPaymentById(Long id);
}
