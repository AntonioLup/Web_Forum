package com.example.database.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")

    private String userName;
    @Column(name = "email")

    private String email;
    @Column(name = "authority")
    private String authority;
    @Column(name = "password")

    private String password;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Post> posts;
    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comment> comments;


    public User() {
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
