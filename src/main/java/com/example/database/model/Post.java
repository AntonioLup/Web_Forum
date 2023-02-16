package com.example.database.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

@Entity
@Table(name = "post")
@Getter @Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    @Lob
    private String content;
    private LocalDate createdOn;
    private LocalDate updatedOn;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "post", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public Post(){}

    public Post(String title, String content, LocalDate createdOn, LocalDate updatedOn, User user, List<Comment> comments) {
        this.title = title;
        this.content = content;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.user = user;
        this.comments = comments;
    }

    public Post updateContent(String updatedContent) {
        setContent(updatedContent);
        setUpdatedOn(LocalDate.now());
        return this;
    }

    public User getBlogUser() {
        return user;
    }

    public void setBlogUser(User user) {
        this.user = user;
    }


    public JsonNode asJson() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.createObjectNode()
                .put("id", id)
                .put("title", title)
                .put("content", content)
                .put("createdOn", createdOn.toString())
                .put("updatedOn", updatedOn.toString())
                .put("user", user.getUserName())
                .put("comments", comments.size());

    }
    public JsonNode asJsonWithComments() {
        return ((ObjectNode) asJson())
                .set("comments", getComments().stream()
                        .map(Comment::asJson)
                        .collect(Collector.of(() -> new ObjectMapper().createArrayNode(), ArrayNode::add, ArrayNode::addAll))
                );
    }
}
