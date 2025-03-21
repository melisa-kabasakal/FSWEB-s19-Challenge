package com.workintech.twitter.controller;

import com.workintech.twitter.dto.TweetRequest;
import com.workintech.twitter.dto.TweetResponse;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tweets")
@CrossOrigin(origins = "http://localhost:3200")
public class TweetController {

    private  TweetService tweetService;

    @Autowired
    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<TweetResponse> getAll() {
        return tweetService.getAll()
                .stream()
                .map(tweet -> new TweetResponse(tweet.getContent()))
                .collect(Collectors.toList());
    }

    @GetMapping("/findByUserId")
    public List<TweetResponse> getTweetsByUserId(@RequestParam Long userId) {
        List<Tweet> tweets = tweetService.getByUserId(userId);
        return tweets.stream()
                .map(tweet -> new TweetResponse(tweet.getContent()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{tweetId}")
    public TweetResponse getById(@PathVariable("tweetId") Long id) {
        Tweet tweet = tweetService.getById(id);
        return new TweetResponse(tweet.getContent());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponse create(@Validated @RequestBody TweetRequest tweetRequest) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());

        tweetService.save(tweet);
        return new TweetResponse(tweet.getContent());
    }

    @PutMapping("/{tweetId}")
    public TweetResponse replaceOrCreate(@PathVariable("tweetId") Long id, @Validated @RequestBody TweetRequest tweetRequest) {
        Tweet tweet = new Tweet();
        tweet.setContent(tweetRequest.getContent());
        tweetService.replaceOrCreate(id, tweet);

        return new TweetResponse(tweet.getContent());
    }

    @PatchMapping("/{tweetId}")
    public TweetResponse update(@PathVariable("tweetId") Long id, @Validated @RequestBody TweetRequest tweetRequest) {
        Tweet tweet = tweetService.getById(id);

        if (tweetRequest.getContent() != null) {
            tweet.setContent(tweetRequest.getContent());
        }

        tweetService.update(id, tweet);
        return new TweetResponse(tweet.getContent());
    }

    @DeleteMapping("/{tweetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("tweetId") Long id) {
        tweetService.deleteById(id);
    }
}
