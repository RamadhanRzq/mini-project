package com.miniproject.pos.posprodemy.service;

import com.miniproject.pos.posprodemy.model.Shipping;
import com.miniproject.pos.posprodemy.model.User;
import com.miniproject.pos.posprodemy.repository.ShippingRepository;
import com.miniproject.pos.posprodemy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUser(){
        return userRepository.findAll();
    }
    public void addUser(User user){
        userRepository.save(user);
    }
    public void removeUserById(Long id){
        userRepository.deleteById(id);
    }
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }
}
