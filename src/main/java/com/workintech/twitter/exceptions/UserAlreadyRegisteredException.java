package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserAlreadyRegisteredException extends TwitterException{
    public UserAlreadyRegisteredException(String message) {

        super(message, HttpStatus.CONFLICT);
    }
}
