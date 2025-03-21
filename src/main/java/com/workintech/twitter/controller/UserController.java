package com.workintech.twitter.controller;

import com.workintech.twitter.dto.UserRequest;
import com.workintech.twitter.dto.UserResponse;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponse> getAll(){
        return userService.getAll()
                .stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail(),user.getTweets()))
                .toList();
    }

    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable("userId") Long id){
        User user = userService.getById(id);
        return new UserResponse(user.getName(), user.getEmail(), user.getTweets());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create (@Validated @RequestBody UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setTweets(userRequest.getTweets());

        userService.create(user);
        return new UserResponse(user.getName(), user.getEmail(), user.getTweets());
    }

    @PutMapping("/{userId}")
    public UserResponse replaceOrCreate(@PathVariable("userId") Long id,@Validated @RequestBody UserRequest userRequest){
        User user = new User();
        user.setId(id);
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setTweets(userRequest.getTweets());

        userService.replaceOrCreate(id, user);
        return new UserResponse(user.getName(), user.getEmail(), user.getTweets());
    }

    @PatchMapping("/{userId}")
    public UserResponse update(@PathVariable("userId") Long id,@Validated @RequestBody UserRequest userRequest){
        User user = userService.getById(id);

        if (userRequest.getName() != null) {
            user.setName(userRequest.getName());
        }
        if (userRequest.getEmail() != null) {
            user.setEmail(userRequest.getEmail());
        }
        if (userRequest.getTweets() != null) {
            user.setTweets(userRequest.getTweets());
        }

        userService.update(id, user);
        return new UserResponse(user.getName(), user.getEmail(), user.getTweets());
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("userId") Long id){

        userService.deleteById(id);
    }
}
