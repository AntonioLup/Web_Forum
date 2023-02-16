package com.example.database.Controller;

import com.example.database.DataTransferObject.ContentRequest;
import com.example.database.Serivice.AuthService;
import com.example.database.model.Comment;
import com.example.database.repository.RepoComment;
import com.example.database.repository.RepoPost;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CommentController {
    @Autowired
    private final RepoPost repoPost;
    @Autowired
    private final RepoComment repoComment;
    @Autowired
    private final AuthService authService;

    @PostMapping("/comments")
    public ResponseEntity addComment(@RequestBody
                                     ContentRequest contentRequest){
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(repoComment.save(createNewComment(contentRequest)).asJson());
    }


    private Comment createNewComment(ContentRequest contentRequest) {
        return new Comment(
                contentRequest.getContent(),
                LocalDate.now(),
                authService.getBlogUser().orElseThrow(),
                repoPost.getReferenceById(contentRequest.getId())
        );
    }

    @PutMapping("/comments")
    public ResponseEntity<JsonNode> updateComment(@RequestBody ContentRequest contentRequest){
        return repoComment.findById(contentRequest.getId())
                .map(comment -> comment.updateContent(contentRequest.getContent()))
                .map(repoComment::save)
                .map(Comment::asJson)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id){
        repoComment.deleteById(id);
        return ResponseEntity.notFound().build();
    }


}
