package com.example.hello.repositories;

import com.example.hello.models.Post;
import com.example.hello.models.PostType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    List<Post> findByPostType(PostType type);

    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :query, '%')) OR p.content LIKE %:query%")
    List<Post> searchPosts(@Param("query") String query);

    List<Post> findByPostTypeAndTitleContainingIgnoreCase(
        PostType postType,
        String title
    );

    @Query("""
SELECT p FROM Post p
ORDER BY 
(p.likeCount * 3) DESC,
p.createdAt DESC
""")
List<Post> getRecommendedPosts();
}
