package com.example.demo.controllers;

import com.example.demo.services.abstracts.AuthService;
import com.example.demo.services.dtos.requests.auth.LoginRequest;
import com.example.demo.services.dtos.requests.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(RegisterRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(LoginRequest request){
        return authService.login(request);
    }
}
