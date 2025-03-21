package com.workintech.twitter.controller;

import com.workintech.twitter.dto.LikeRequest;
import com.workintech.twitter.dto.LikeResponse;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.service.LikeService;
import com.workintech.twitter.service.UserService;
import com.workintech.twitter.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private final LikeService likeService;
    private final UserService userService;
    private final TweetService tweetService;

    @Autowired
    public LikeController(LikeService likeService, UserService userService, TweetService tweetService) {
        this.likeService = likeService;
        this.userService = userService;
        this.tweetService = tweetService;
    }

    @GetMapping
    public List<LikeResponse> getAll() {
        return likeService.getAll()
                .stream()
                .map(like -> new LikeResponse("Like found", like.getUser().getId(), like.getTweet().getId()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{likeId}")
    public LikeResponse getById(@PathVariable Long likeId) {
        Like like = likeService.getById(likeId);
        return new LikeResponse("Like found", like.getUser().getId(), like.getTweet().getId());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponse create(@Validated @RequestBody LikeRequest likeRequest) {
        User user = userService.getById(likeRequest.getUserId());
        Tweet tweet = tweetService.getById(likeRequest.getTweetId());

        Like like = new Like();
        like.setUser(user);
        like.setTweet(tweet);

        likeService.save(like);
        return new LikeResponse("Like created", like.getUser().getId(), like.getTweet().getId());
    }

    @DeleteMapping("/{likeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long likeId) {
        likeService.deleteById(likeId);
    }
}
