package com.example.database.Controller;

import com.example.database.DataTransferObject.ContentRequest;
import com.example.database.Serivice.AuthService;
import com.example.database.repository.RepoPost;
import com.example.database.repository.RepoUser;
import com.example.database.model.Post;
import com.example.database.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private final RepoPost repoPost;
    @Autowired
    private final AuthService authService;
    @Autowired
    public PostController(RepoPost repoPost, AuthService authService) {
        this.repoPost = repoPost;
        this.authService = authService;
    }

    //  mostrar todos
    @GetMapping("/posts")
    public ResponseEntity <List<JsonNode>> findAll(){
       return ResponseEntity.ok(
               repoPost.findAll()
                       .stream()
                       .map(Post::asJson)
                       .toList()
       );
    }

//  mostrar uno
    @GetMapping("/posts/{id}")
    public ResponseEntity<JsonNode> findById(@PathVariable Long id){
        return repoPost.findById(id)
//        Una lambda sigue siendo una función es decir un método por lo tanto en programación funcional podemos sustituir una función lambda con un método tradicional por medio de la referencia a métodos usando el operador ::
                .map(Post::asJsonWithComments)
//                .map(Post -> Post.asJsonWithComments)

                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
//  postear
    @PostMapping("/posts")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<JsonNode> post(@RequestBody ContentRequest contentDTO){

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(repoPost.save(newPost(contentDTO))
                        .asJson());
    }
//    nuevo post
    private Post newPost(ContentRequest contentDTO){
        return new Post(
                contentDTO.getTitle(),
                contentDTO.getContent(),
                LocalDate.now(),
                LocalDate.now(),
                authService.getBlogUser().orElseThrow(),
                new ArrayList<>()
        );
    }
//  actualizar
    @PutMapping("/posts")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<JsonNode> updatePost(@RequestBody ContentRequest contentDTO){
        return repoPost.findById(contentDTO.getId())
                .map(post -> post.updateContent(contentDTO.getContent()))
                .map(post -> ResponseEntity.ok(repoPost.save(post).asJson()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/posts/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<User> delete(@PathVariable Long id){
        repoPost.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
