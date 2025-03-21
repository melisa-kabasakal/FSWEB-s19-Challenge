package com.workintech.twitter.dto;

import com.workintech.twitter.entity.Tweet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String name;

    private String email;

    private String password;

    private List<Tweet> tweets;


}
