package com.workintech.twitter.service;

import com.workintech.twitter.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long id);
    User create(User user);
    User replaceOrCreate(Long id, User user);
    User update(Long id, User user);
    void deleteById(Long id);
}
