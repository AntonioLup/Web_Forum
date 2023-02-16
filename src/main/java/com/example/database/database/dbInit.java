package com.example.database.database;

import com.example.database.repository.RepoUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.database.utils.BlogFactory;

@Component
public class dbInit {
    private final RepoUser repoUser;

    public dbInit(RepoUser repoUser) {
        this.repoUser = repoUser;
    }

    @PostConstruct
    public void init(){
        BlogFactory.BLOG_USERS.forEach(repoUser::save);
        repoUser.save(null);
    }
}
