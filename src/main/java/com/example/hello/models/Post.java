package com.example.hello.models;
import jakarta.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private int likes;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author ;

    public Post() {}

    // ✅ Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public int getLikes() { return likes; }
    public void setLikes(int likes) { this.likes = likes; }

    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }

    public String getPostDetails(){
        return this.id +"\n"+ this.title +"\n"+this.content ;
    }
}
