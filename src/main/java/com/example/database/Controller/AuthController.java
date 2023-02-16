package com.example.database.Controller;

import com.example.database.DataTransferObject.AuthRequest;
import com.example.database.Serivice.AuthService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<JsonNode> login(@RequestBody AuthRequest loginRequest)
            throws Exception{
        try{
            return authService.login(loginRequest);
        }catch(BadCredentialsException e){
            throw new Exception("Incorrect username or password", e);
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<JsonNode> signup(@RequestBody AuthRequest loginRequest){
        return authService.signup(loginRequest)
                ? ResponseEntity.ok(responseMessage(loginRequest.getUserName()))
                : ResponseEntity.unprocessableEntity().body(responseMessage(loginRequest.getUserName()));
    }
    private ObjectNode responseMessage(String userName) {
        return new ObjectMapper().createObjectNode()
                .put("userName", userName);
    }
}
