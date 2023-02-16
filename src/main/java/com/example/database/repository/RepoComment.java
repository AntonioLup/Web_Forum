package com.example.database.repository;

import com.example.database.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface RepoComment extends JpaRepository<Comment, Long> {
}
