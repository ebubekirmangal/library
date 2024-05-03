package com.example.demo.services.abstracts;

import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;

public interface AuthService {

    void register(UserRegisterRequest request);

    String login(UserLoginRequest request);
}
