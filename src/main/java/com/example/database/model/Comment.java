package com.example.database.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Lob
    private String comment;
    private LocalDate createdOn;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment (){

    }

    public Comment(String comment, LocalDate createdOn, User user, Post post) {
        this.id = id;
        this.comment = comment;
        this.createdOn = createdOn;
        this.user = user;
        this.post = post;
    }

    public Comment updateContent(String content) {
        setComment(content);
        setCreatedOn(LocalDate.now());
        return this;
    }

    public JsonNode asJson() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.createObjectNode()
                .put("id", id)
                .put("comment", comment)
                .put("createdOn", createdOn.toString())
                .put("user", user.getUserName());
    }
}
