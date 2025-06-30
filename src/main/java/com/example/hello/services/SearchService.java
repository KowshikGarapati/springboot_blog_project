package com.example.hello.services;

import com.example.hello.models.Post;
import com.example.hello.models.User;
import com.example.hello.repositories.PostRepository;
import com.example.hello.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public SearchService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCase(query);
    }

    public List<Post> searchPosts(String query) {
        return postRepository.searchPosts(query);
    }
}
