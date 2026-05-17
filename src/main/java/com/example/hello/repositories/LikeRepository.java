package com.example.hello.repositories;

import com.example.hello.models.Like;
import com.example.hello.models.Post;
import com.example.hello.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserAndPost(User user, Post post);

    long countByPost(Post post);
}