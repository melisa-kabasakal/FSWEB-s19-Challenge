package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TweetNotFoundException extends TwitterException {
    public TweetNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
