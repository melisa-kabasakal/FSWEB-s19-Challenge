package com.workintech.twitter.dto;

import com.workintech.twitter.entity.Tweet;

import java.util.List;

public record UserResponse(String name, String email, List<Tweet> tweets) {

}
