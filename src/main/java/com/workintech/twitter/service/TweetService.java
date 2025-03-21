package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {
    List<Tweet> getAll();
    Tweet getById(Long id);
    Tweet save(Tweet tweet);
    Tweet update(Long id, Tweet tweet);
    Tweet replaceOrCreate(Long id, Tweet tweet);
    void deleteById(Long id);

    List<Tweet> getByUserId(Long userId);
}
