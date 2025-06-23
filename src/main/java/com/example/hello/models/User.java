package com.example.hello.models;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String address;
    private String genre;
    private String username;
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Post> posts ;

    public User() {}

    // Getters and setters

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAdress() { return address; }
    public void setAdress(String adress) { this.address = adress; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public List<Post> getPosts() { return posts; }
    public void setPosts(List<Post> posts) { this.posts = posts; }

    public void getDetails(){
        System.out.println(this.id +" "+ this.firstname+" "+ this.email);
    }

}
