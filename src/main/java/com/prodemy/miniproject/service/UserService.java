package com.prodemy.miniproject.service;

import java.util.List;

import com.prodemy.miniproject.dto.UserDto;
import com.prodemy.miniproject.model.User;

public interface UserService {

    public void saveUser(UserDto userDto);
    public void updateUser (UserDto userDto);
    public User findUserByEmail(String email);
    public List<UserDto> findAllUsers();
    UserDto getUserById(Long id);

    public User getCurrentUser();
    public void deleteUserById(Long id);

}
