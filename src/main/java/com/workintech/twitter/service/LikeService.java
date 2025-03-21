package com.workintech.twitter.service;

import com.workintech.twitter.entity.Like;

import java.util.List;

public interface LikeService {
    List<Like> getAll();
    Like getById(Long likeId);
    Like save(Like like);
    void deleteById(Long likeId);
}
