package com.example.database.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    @NotBlank
    @Size(max = 20)
    private String userName;
    @Column(name = "email")
    @NotBlank
    @Size(max = 50)
    private String email;
    @Column(name = "authority")
    private String authority;
    @Column(name = "password")
    @NotBlank
    @Size(max = 120)
    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Post> posts;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comment> comments;



    public User(@NotBlank @Size(max = 20) String userName, @NotBlank @Size(max = 120) String password, boolean b, boolean b1, boolean b2, boolean b3, Object authorities) {
    }

    public User(String userName, String authority, String password, String email) {
        this.userName = userName;
        this.authority = authority;
        this.password = password;
        this.email = email;
        this.posts = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
