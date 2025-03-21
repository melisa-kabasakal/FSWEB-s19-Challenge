package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommentNotFoundException extends TwitterException{
    public CommentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
