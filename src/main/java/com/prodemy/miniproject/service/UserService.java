package com.prodemy.miniproject.service;

import java.util.List;

import com.prodemy.miniproject.dto.UserDto;
import com.prodemy.miniproject.model.User;

public interface UserService {
	
	void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    List<UserDto> findAllUsers();

}
