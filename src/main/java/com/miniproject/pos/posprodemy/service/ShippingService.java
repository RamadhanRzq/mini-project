package com.miniproject.pos.posprodemy.service;

import com.miniproject.pos.posprodemy.model.Payment;
import com.miniproject.pos.posprodemy.model.Shipping;
import com.miniproject.pos.posprodemy.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingService {
    @Autowired
    ShippingRepository shippingRepository;
    public List<Shipping> getAllShipping(){
        return shippingRepository.findAll();
    }
    public void addShipping(Shipping shipping){
        shippingRepository.save(shipping);
    }
    public void removeShippingById(Long id){
        shippingRepository.deleteById(id);
    }
    public Optional<Shipping> getShippingById(Long id){
        return shippingRepository.findById(id);
    }
}
