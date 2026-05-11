package com.example.hello.controllers;

import com.example.hello.models.Post;
import com.example.hello.models.PostType;
import com.example.hello.services.ExploreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ExploreController {

    @Autowired
    private ExploreService exploreService;

    @GetMapping("/explore")
    public String ExplorePage(Model model){

        List<Post> reversedPosts = exploreService.ExplorePosts();
        model.addAttribute("posts", reversedPosts);
        return "explore" ;

    }

    @GetMapping("/explore/stories")
    public String storiesPage(Model model){

        model.addAttribute(
            "posts",
            exploreService.getPostsByType(PostType.STORY)
        );

        return "stories";
    }

    @GetMapping("/explore/poems")
    public String poemsPage(Model model){

        model.addAttribute(
            "posts",
            exploreService.getPostsByType(PostType.POEM)
        );

        return "poems";
    }

    /*@GetMapping("/explore/essays")
    public String essaysPage(Model model){

        model.addAttribute(
            "posts",
            exploreService.getPostsByType(PostType.ESSAY)
        );

        return "essays";
    }*/

    @GetMapping("/explore/reviews")
    public String reviewsPage(Model model){

        model.addAttribute(
            "posts",
            exploreService.getPostsByType(PostType.REVIEW)
        );

        return "reviews";
    }
}
