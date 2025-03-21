package com.workintech.twitter.service.impl;

import com.workintech.twitter.entity.Like;
import com.workintech.twitter.repository.LikeRepository;
import com.workintech.twitter.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like getById(Long likeId) {
        return likeRepository.findById(likeId).orElseThrow(() -> new RuntimeException("Like not found"));
    }

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);  // Save the like and return the saved object
    }

    @Override
    public void deleteById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
