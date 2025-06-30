package com.example.hello.controllers;


import com.example.hello.models.Post;
import com.example.hello.models.User;
import com.example.hello.services.SearchService;
import com.example.hello.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String showSearchPage() {
        return "search"; // Your Thymeleaf HTML file
    }

    @PostMapping("/search")
    public String handleSearch(@RequestParam("query") String query, Model model) throws Exception{
        List<User> users = searchService.searchUsers(query);
        System.out.println("got users from query info");
        List<Post> posts = searchService.searchPosts(query);
        System.out.println("got posts from query info");

        model.addAttribute("users", users);
        model.addAttribute("posts", posts);
        model.addAttribute("query", query); // keep input filled

        return "search";
    }
}