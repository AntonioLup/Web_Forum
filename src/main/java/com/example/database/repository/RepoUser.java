package com.example.database.repository;

import com.example.database.model.Post;
import com.example.database.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface RepoUser extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
}
