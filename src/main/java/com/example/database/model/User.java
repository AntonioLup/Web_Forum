package com.example.database.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.mapping.Set;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.AUTO;
import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Getter
@Setter
@ToString
@Table(name="users")

public class User {
    @Id
//    @SequenceGenerator(
//            name = "user_seq",
//            sequenceName = "user_seq",
//            allocationSize = 1
//    )
    @GeneratedValue(
            strategy = AUTO
//            generator = "user_seq"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "userName")

    private String userName;
    @Column(name = "email")

    private String email;
    @Column(name = "authority")
    private String authority;
    @Column(name = "password")

    private String password;
//    fetch = si quieres que traiga los datos cargados
//    cascade = como se realiza
//    onetoone (usuario a cliente)
//    onetomany (cliente a muchos posts)
//    manytoone ()
//    manytomany (muchos clientes a muchos likes)

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
