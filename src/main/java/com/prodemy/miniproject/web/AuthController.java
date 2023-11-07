package com.prodemy.miniproject.web;

import com.prodemy.miniproject.global.GlobalData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.prodemy.miniproject.service.UserService;
import com.prodemy.miniproject.dto.UserDto;
import com.prodemy.miniproject.model.User;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	private UserService userService;

	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	 // Handler method to handle login request
    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }
    
    @GetMapping("/register")
    public String register(Model model){
    	UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null && existingUser.getEmail() != null && !existingUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }
    
    // Handler method to handle list of users
//    @GetMapping("/users")
//    public String users(Model model){
//        List<UserDto> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }
	

}
