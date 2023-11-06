package com.prodemy.miniproject.service;

import com.prodemy.miniproject.model.Shipping;
import com.prodemy.miniproject.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingServiceImpl implements ShippingService{
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
