package com.prodemy.miniproject.service;

import java.util.List;
import java.util.Optional;

import com.prodemy.miniproject.dto.UserDto;
import com.prodemy.miniproject.model.User;

public interface UserService {

    public void saveUser(UserDto userDto);
    public User findUserByEmail(String email);
    public List<UserDto> findAllUsers();
    public Optional<User> getUserById(Long id);
    public void deleteUserById(Long id);

}
