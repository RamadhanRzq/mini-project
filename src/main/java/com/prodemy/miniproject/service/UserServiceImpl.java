package com.prodemy.miniproject.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prodemy.miniproject.dto.UserDto;
import com.prodemy.miniproject.model.Role;
import com.prodemy.miniproject.model.User;
import com.prodemy.miniproject.repository.RoleRepository;
import com.prodemy.miniproject.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
    	if (userRepository.count() == 0)
    	{
    		User user = new User();
	        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        
        	Role role = roleRepository.findByName("ROLE_ADMIN");
            if(role == null)
            {
                role = checkRoleExist();
            }
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
    	}
    	else
    	{
    		User user = new User();
	        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
	        user.setEmail(userDto.getEmail());
	        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
	        
    		Role role = roleRepository.findByName("ROLE_USER");
	        if(role == null)
	        {
	            role = checkRoleExist();
	        }
	        user.setRoles(Arrays.asList(role));
	        userRepository.save(user);
    	}
        
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());

        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> Users = userRepository.findById(id);
        User user = null;
        if(Users.isPresent())
        {
            user = Users.get();
        }
        else
        {
            throw new RuntimeException("Student not found for id: " + id);
        }
        return mapToUserDto(user);
    }

    @Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails userDetails) {
            return findUserByEmail(userDetails.getUsername());
        } else {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        this.userRepository.deleteById(id);
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        if (roleRepository.count() == 0)
        {
        	Role role = new Role();
        
        	role.setName("ROLE_ADMIN");
            return roleRepository.save(role);
        }
        else
        {
        	Role role = new Role();
        	
        	role.setName("ROLE_USER");
            return roleRepository.save(role);
        }
        
    }

}
