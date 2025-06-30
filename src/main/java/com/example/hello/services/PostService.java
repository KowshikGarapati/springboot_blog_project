package com.example.hello.services;


import com.example.hello.models.Post;
import com.example.hello.models.User;
import com.example.hello.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow() ;
    }

    public Post getPostByTitle(String title){
        return postRepository.findByTitle(title) ;
    }


    public Post addPost(Post post){
        return postRepository.save(post) ;
    }
}
