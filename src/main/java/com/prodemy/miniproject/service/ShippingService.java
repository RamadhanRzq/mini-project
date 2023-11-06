package com.prodemy.miniproject.service;

import com.prodemy.miniproject.model.Shipping;

import java.util.List;
import java.util.Optional;

public interface ShippingService {
    public List<Shipping> getAllShipping();
    public void addShipping(Shipping shipping);
    public void removeShippingById(Long id);
    public Optional<Shipping> getShippingById(Long id);
}
