package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TwitterException extends RuntimeException{
    private HttpStatus status;

    public TwitterException(String message,HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
