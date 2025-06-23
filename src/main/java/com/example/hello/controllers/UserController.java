package com.example.hello.controllers;


import com.example.hello.models.User;
import com.example.hello.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/allUsers")
    public List<User> displayAllUsers(){
        List<User> allUsers = service.getAllUsers();
        return allUsers ;
    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser(@ModelAttribute User user) {
        service.addUser(user);
        System.out.println("Logic executed!");
        // redirect to another route (e.g., /next)
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users/allUsers"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/getSessionId")
    public String getSessionId(HttpServletRequest http){
        return http.getSession().getId();
    }

//use this same method to display the logged in user's data on the home page
    @GetMapping("/loggedUser")
    public User getLoggedUser(Principal principal){
        String loggedUserName = principal.getName() ;
        User loggedUser = service.getUserByName(loggedUserName);
        return loggedUser ;
    }

}
/* @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirm_password*/