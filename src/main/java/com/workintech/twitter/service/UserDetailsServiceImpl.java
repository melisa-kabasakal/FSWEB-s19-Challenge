package com.workintech.twitter.service;

import com.workintech.twitter.entity.UserSecurity;
import com.workintech.twitter.repository.UserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserSecurityRepository userSecurityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userSecurityRepository
                .findByEmail(username)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
    }
}
