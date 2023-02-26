package com.example.database.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import com.example.database.utils.BlogFactory;

@Component
public class DbInit {
    private final RepoUser repoUser;

    public DbInit(RepoUser repoUser) {
        this.repoUser = repoUser;
    }

//    @PostConstruct
//    public void init(){
//        BlogFactory.BLOG_USERS.forEach(repoUser::save);
//        repoUser.save(null);
//    }
}
