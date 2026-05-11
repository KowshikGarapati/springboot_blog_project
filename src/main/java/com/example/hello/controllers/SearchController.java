package com.example.hello.controllers;


import com.example.hello.models.Post;
import com.example.hello.models.PostType;
import com.example.hello.models.User;
import com.example.hello.services.SearchService;
import com.example.hello.services.UserService;
import com.example.hello.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    private final SearchService searchService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService ;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public String showSearchPage() {
        return "search"; // Your Thymeleaf HTML file
    }

    @PostMapping("/search")
public String search(
        @RequestParam String query,
        Model model
) {

    model.addAttribute("query", query);

    model.addAttribute(
            "users",
            userService.searchUsers(query)
    );

    model.addAttribute(
            "stories",
            postService.searchByType(query, PostType.STORY)
    );

    model.addAttribute(
            "poems",
            postService.searchByType(query, PostType.POEM)
    );

    model.addAttribute(
            "reviews",
            postService.searchByType(query, PostType.REVIEW)
    );

    model.addAttribute(
            "articles",
            postService.searchByType(query, PostType.ARTICLE)
    );

    model.addAttribute(
            "quotes",
            postService.searchByType(query, PostType.QUOTE)
    );

    return "search";
}

    @GetMapping("/profile/{username}")
    public String getSearchedUser(@PathVariable("username") String username, Model model , Principal principal){
        User searchedUser = userService.getUserByName(username);
        User currentUser = userService.getUserByName(principal.getName());
        model.addAttribute("searcheduser", searchedUser);
        model.addAttribute("isFollowedByCurrentuser", userService.isFollowing(currentUser, searchedUser));
        return "profile" ;
    }
}