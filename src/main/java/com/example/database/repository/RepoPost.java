package com.example.database.repository;

import com.example.database.model.Post;
import com.example.database.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface RepoPost extends JpaRepository<Post, Long> {
}
