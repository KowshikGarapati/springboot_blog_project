package com.example.hello.controllers;

import com.example.hello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class FollowController {

    @Autowired
    private UserService userService;

    @GetMapping("/follow/{username}")
    public String followUserRoute(@PathVariable String username, Principal principal) {
        userService.followUser(principal.getName(), username);
        return "redirect:/profile/" + username;
    }

    @GetMapping("/unfollow/{username}")
    public String unfollowUserRoute(@PathVariable String username, Principal principal) {
        userService.unfollowUser(principal.getName(), username);
        return "redirect:/profile/" + username;
    }
}

