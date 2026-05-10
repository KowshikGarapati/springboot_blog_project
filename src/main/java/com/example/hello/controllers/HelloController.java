package com.example.hello.controllers;

import com.example.hello.models.User;
import com.example.hello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import com.example.hello.repositories.UserRepository;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HelloController {
    @Autowired
    private UserService service;

    @GetMapping("/")
    public String hello(Principal principal, Model model){
        User loggedUser = service.getUserByName(principal.getName());
        model.addAttribute("user", loggedUser);
        return "home" ;
    }

    @GetMapping("/addeduser")
    public String addeduser(@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String confirm_password, Model model){
        Map<String, String> data = new HashMap<>();
        data.put("firstname", firstname );
        data.put("lastname", lastname);
        data.put("email", email);
        data.put("password", password);
        data.put("confirm_password", confirm_password);
        model.addAllAttributes(data);
        return "htmlFileForTesting" ;
    }


    @GetMapping("/newPost")
    public String renderPostCreatingForm(Model model, Principal principal){
        User loggedUser = service.getUserByName(principal.getName());
        model.addAttribute("user", loggedUser);
        return "postCreatingForm" ;
    }

    @GetMapping("/editprofile")
    public String editprofile(Model model, Principal principal){
        User loggedUser = service.getUserByName(principal.getName());
        model.addAttribute("user", loggedUser);
        return "editProfile";
    }

    @PostMapping("/updateProfile")
    public String saveChanges(@RequestParam String username,
                              @RequestParam String firstname,
                              @RequestParam String lastname,
                              @RequestParam String email,
                              @RequestParam String password,
                              @RequestParam String address,
                              @RequestParam String phone,
                              @RequestParam String genre,
                              Principal principal){
        service.updateUser(principal.getName(), username, firstname, lastname, email, password, address, phone, genre );
        return "redirect:/";
    }
}


