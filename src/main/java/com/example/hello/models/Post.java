package com.example.hello.models;
import com.example.hello.models.PostType;
import java.time.LocalDateTime;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private int likes;
    private int views;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author ;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    private String bookTitle;

    private String bookAuthor;

    private Integer rating;

    private LocalDateTime createdAt;

    public String getPostDetails(){
        return this.id.toString() + " " + this.title + "  " +this.content ;
    }

    @PrePersist
    public void createdNow(){
        createdAt = LocalDateTime.now();
    }
}
