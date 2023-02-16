package com.example.database.Serivice;

import com.example.database.repository.RepoUser;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class UserDetailsImp implements UserDetailsService {
    @Autowired
    private final RepoUser repoUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.example.database.model.User user = repoUser.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("no user found :" + username));
        return  this.mapToUser(user);

    }

    private User mapToUser(com.example.database.model.User user) {
        return new User(
                user.getUserName(),
                user.getPassword(),
                true,true,true,true,
                getAuthorities("ROLE_" + user.getAuthority())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role_user) {
        return Collections.singletonList(new SimpleGrantedAuthority(role_user));
    }

}
