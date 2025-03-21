package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.exceptions.CommentNotFoundException;
import com.workintech.twitter.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if(optionalComment.isPresent()){
            return optionalComment.get();
        }
        throw new CommentNotFoundException("Not Found Comment " + id);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Long id, Comment comment) {
        return null;
    }

    @Override
    public Comment replaceOrCreate(Long id, Comment comment) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
