package com.workintech.twitter.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TwitterErrorResponse {

    private String message;
    private int status;

    public TwitterErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}
