package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserNotFoundException extends TwitterException{
    public UserNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
