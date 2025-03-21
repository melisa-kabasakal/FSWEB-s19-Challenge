package com.workintech.twitter.dto;

import com.workintech.twitter.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetweetRequest {

    private Long userId;
    private Long tweetId;

}
