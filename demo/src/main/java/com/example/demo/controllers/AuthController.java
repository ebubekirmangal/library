package com.example.demo.controllers;

import com.example.demo.services.abstracts.AuthService;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRegisterRequest request){
        authService.register(request);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@RequestBody UserLoginRequest request){

        return  authService.login(request);
    }

}
