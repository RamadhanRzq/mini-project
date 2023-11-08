package com.prodemy.miniproject.controller;

import com.prodemy.miniproject.model.User;
import com.prodemy.miniproject.service.UserService;
import com.prodemy.miniproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    public UserController(UserServiceImpl userServiceImpl){
        super();
        this.userService = userServiceImpl;
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "detailUser";
    }

}
