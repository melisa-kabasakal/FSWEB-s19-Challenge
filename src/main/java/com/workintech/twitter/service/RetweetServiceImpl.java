package com.workintech.twitter.service;

import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.exceptions.TwitterException;
import com.workintech.twitter.repository.RetweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RetweetServiceImpl implements RetweetService {

    private final RetweetRepository retweetRepository;

    @Autowired
    public RetweetServiceImpl(RetweetRepository retweetRepository) {
        this.retweetRepository = retweetRepository;
    }

    @Override
    public List<Retweet> getAll() {
        return retweetRepository.findAll();
    }

    @Override
    public Retweet getById(Long id) {
        return retweetRepository.findById(id)
                .orElseThrow(() -> new TwitterException("Retweet not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Retweet save(Retweet retweet) {
        return retweetRepository.save(retweet);
    }

    @Override
    public void deleteById(Long id) {
        if (!retweetRepository.existsById(id)) {
            throw new TwitterException("Retweet not found", HttpStatus.NOT_FOUND);
        }
        retweetRepository.deleteById(id);
    }
}

