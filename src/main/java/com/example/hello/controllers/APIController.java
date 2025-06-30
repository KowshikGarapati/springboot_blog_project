package com.example.hello.controllers;


import com.example.hello.models.Post;
import com.example.hello.models.User;
import com.example.hello.services.PostService;
import com.example.hello.services.UserService;
import jakarta.servlet.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.security.Principal;
import java.util.*;

@RestController
public class APIController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/users/allUsers")
    public List<User> displayAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        return allUsers ;
    }

    @PostMapping("/users/addUser")
    public ResponseEntity<Void> addUser(@ModelAttribute User user) {
        userService.addUser(user);
        System.out.println("Logic executed!");
        // redirect to another route (e.g., /next)
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/users/allUsers"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @PostMapping("/users/createNewPost")
    public ResponseEntity<Void> createNewPost(@ModelAttribute Post post, Principal principal) {
        post.setAuthor(userService.getUserByName(principal.getName()));
        postService.addPost(post);
        System.out.println("Logic executed!");
        // redirect to another route (e.g., /next)
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/"));
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/users/getSessionId")
    public String getSessionId(HttpServletRequest http){
        return http.getSession().getId();
    }

//use this same method to display the logged in user's data on the home page
    @GetMapping("/loggedUser")
    public User getLoggedUser(Principal principal){
        String loggedUserName = principal.getName() ;
        User loggedUser = userService.getUserByName(loggedUserName);
        return loggedUser ;
    }

}



/* @RequestParam String firstname,
            @RequestParam String lastname,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirm_password*/