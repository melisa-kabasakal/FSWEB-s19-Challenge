package com.workintech.twitter.service;

import com.workintech.twitter.entity.Retweet;

import java.util.List;

public interface RetweetService {
    List<Retweet> getAll();
    Retweet getById(Long id);
    Retweet save(Retweet retweet);
    void deleteById(Long id);
}
