package com.workintech.twitter.service;

import com.workintech.twitter.entity.UserSecurity;

public interface AuthService {
    UserSecurity register(String mail, String password);
}
