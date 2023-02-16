package com.example.database.database;

import com.example.database.repository.RepoUser;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class dbInit {
    private final RepoUser repoUser;

    public dbInit(RepoUser repoUser) {
        this.repoUser = repoUser;
    }

//    @PostConstruct
//    public void init(){
//        repoUser.USERS.forEach(repoUser::save);
//        repoUser.save(null);
//    }
}
