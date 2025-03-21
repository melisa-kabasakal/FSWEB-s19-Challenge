package com.workintech.twitter.service;

import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetServiceImpl implements TweetService{

    private TweetRepository tweetRepository;

    @Autowired
    public TweetServiceImpl(TweetRepository tweetRepository){
        this.tweetRepository = tweetRepository;
    }
    @Override
    public List<Tweet> getAll() {
        return tweetRepository.findAll();
    }

    @Override
    public Tweet getById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(()-> new TweetNotFoundException("User not found " + id));
    }

    @Override
    public Tweet save(Tweet tweet) {
        return null;
    }

    @Override
    public Tweet update(Long id, Tweet tweet) {
        return null;
    }

    @Override
    public Tweet replaceOrCreate(Long id, Tweet tweet) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Tweet> getByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }
}
