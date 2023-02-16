package com.example.database.Serivice;

import com.example.database.DataTransferObject.AuthRequest;
import com.example.database.model.User;
import com.example.database.repository.RepoUser;
import com.example.database.security.JwtProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService  {
    @Autowired
    private final RepoUser repoUser;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtProvider jwtProvider;



    public Optional<User> getBlogUser(){
        String blogUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return repoUser.findByUserName(blogUser);
    }

    public boolean signup(AuthRequest authRequest) {
        User blogUser = this.mapToBlogUser(authRequest);
        return repoUser.findByUserName(blogUser.getUserName())
                .map(user -> false)
                .orElseGet(() -> {
                    repoUser.save(blogUser);
                    return true;
                });
    }

    private User mapToBlogUser(AuthRequest authRequest) {
        return new User(
                authRequest.getUserName(),
                "USER",
                passwordEncoder.encode(authRequest.getPassword()),
                authRequest.getEmail()
        );
    }

    public ResponseEntity<JsonNode> login(AuthRequest loginRequest){
        Authentication authManager;
        try{
            authManager = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUserName(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authManager);
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(badCredentialsResponseNode(loginRequest));
        }
        return ResponseEntity.ok(loginSuccessResponseNode(authManager));
    }



    private ObjectNode badCredentialsResponseNode(AuthRequest loginRequest) {
        return new ObjectMapper().createObjectNode()
                .put("error", badCredentialsMessage(loginRequest.getUserName()));
    }

    private String badCredentialsMessage(String userName) {
        return repoUser.findByUserName(userName)
                .map(user -> "invalid password")
                .orElse("invalid username");
    }


    private ObjectNode  loginSuccessResponseNode(Authentication authManager) {
        return new ObjectMapper().createObjectNode()
                .put("userName", authManager.getName())
               .put("authorities", authenticaties(authManager.getAuthorities()))
                .put("jwt", jwtProvider.generateToken(authManager));
    }

    public String authenticaties(Collection<? extends GrantedAuthority> grantedAuthority) throws AuthenticationException {
        return grantedAuthority.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
    }

    public Optional<User> getUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return repoUser.findByUserName(userName);
    }
}
