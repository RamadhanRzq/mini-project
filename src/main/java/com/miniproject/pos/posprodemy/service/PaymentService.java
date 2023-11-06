package com.miniproject.pos.posprodemy.service;

import com.miniproject.pos.posprodemy.model.Payment;
import com.miniproject.pos.posprodemy.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    public List<Payment> getAllPayment(){
        return paymentRepository.findAll();
    }
    public void addPayment(Payment payment){
        paymentRepository.save(payment);
    }
    public void removePaymentById(Long id){
        paymentRepository.deleteById(id);
    }
    public Optional<Payment> getPaymentById(Long id){
        return paymentRepository.findById(id);
    }
}
