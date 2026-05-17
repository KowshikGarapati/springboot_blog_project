package com.example.hello.services;

import com.example.hello.models.Like;
import com.example.hello.models.Post;
import com.example.hello.models.User;
import com.example.hello.repositories.LikeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeService {

    @Autowired
    private LikeRepository likeRepository;

    public void toggleLike(User user, Post post){

        Optional<Like> existingLike =
                likeRepository.findByUserAndPost(user, post);

        if(existingLike.isPresent()){

            likeRepository.delete(existingLike.get());

        } else {

            Like like = Like.builder()
                    .user(user)
                    .post(post)
                    .build();

            likeRepository.save(like);
        }
    }

    public long getLikeCount(Post post){
        return likeRepository.countByPost(post);
    }

    public boolean hasUserLiked(User user, Post post){

        return likeRepository
                .findByUserAndPost(user, post)
                .isPresent();
    }
}