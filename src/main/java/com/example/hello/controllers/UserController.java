package com.example.hello.controllers;


import com.example.hello.models.User;
import com.example.hello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    //@GetMapping
    public String displayAllUsers(Model model){
        List<User> allUsers = service.getAllUsers();
        model.addAttribute("allUsers" , allUsers);
        return "" ;
    }

}
