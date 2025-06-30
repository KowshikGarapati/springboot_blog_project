package com.example.hello.services;

import com.example.hello.models.Post;
import com.example.hello.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExploreService {

    private final PostRepository postRepository;

    public ExploreService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> ExplorePosts() {
        return postRepository.findAll();
    }
}
