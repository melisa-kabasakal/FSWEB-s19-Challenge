package com.workintech.twitter.controller;

import com.workintech.twitter.dto.RegisterRequest;
import com.workintech.twitter.dto.RegisterResponse;
import com.workintech.twitter.entity.UserSecurity;
import com.workintech.twitter.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@Validated @RequestBody RegisterRequest request){
        UserSecurity userSecurity = authService.register(request.getEmail(), request.getPassword());

        return new RegisterResponse(userSecurity.getEmail(), "User successfully regitered");

    }
}
