package com.workintech.twitter.controller;

import com.workintech.twitter.dto.RetweetRequest;
import com.workintech.twitter.dto.RetweetResponse;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.service.RetweetService;
import com.workintech.twitter.service.UserService;
import com.workintech.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/retweets")
public class RetweetController {

    private final RetweetService retweetService;
    private final UserService userService;
    private final TweetService tweetService;

    @Autowired
    public RetweetController(RetweetService retweetService, UserService userService, TweetService tweetService) {
        this.retweetService = retweetService;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<RetweetResponse> getAll() {
        return retweetService.getAll()
                .stream()
                .map(retweet -> new RetweetResponse("Retweet found", retweet.getUser().getId(), retweet.getTweet().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{retweetId}")
    public RetweetResponse getById(@PathVariable Long retweetId) {
        Retweet retweet = retweetService.getById(retweetId);
        return new RetweetResponse("Retweet found", retweet.getUser().getId(), retweet.getTweet().getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetResponse create(@Validated @RequestBody RetweetRequest retweetRequest) {
        User user = userService.getById(retweetRequest.getUserId());
        Tweet tweet = tweetService.getById(retweetRequest.getTweetId());

        Retweet retweet = new Retweet();
        retweet.setUser(user);
        retweet.setTweet(tweet);

        retweetService.save(retweet);
        return new RetweetResponse("Retweet created", retweet.getUser().getId(), retweet.getTweet().getId());
    }

    @DeleteMapping("/{retweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long retweetId) {
        retweetService.deleteById(retweetId);
    }
}
