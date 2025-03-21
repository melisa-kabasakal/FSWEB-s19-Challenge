package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException){
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                twitterException.getMessage(),
                twitterException.getStatus().value()
        );
        return new ResponseEntity<>(twitterErrorResponse, twitterException.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception){
        TwitterErrorResponse twitterErrorResponse = new TwitterErrorResponse(
                exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(twitterErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
