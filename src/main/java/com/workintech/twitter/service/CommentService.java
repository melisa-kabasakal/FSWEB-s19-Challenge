package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getById(Long id);
    Comment save(Comment comment);
    Comment update(Long id, Comment comment);
    Comment replaceOrCreate(Long id, Comment comment);
    void deleteById(Long id);
}
