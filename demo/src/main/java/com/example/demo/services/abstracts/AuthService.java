package com.example.demo.services.abstracts;

import com.example.demo.services.dtos.requests.auth.LoginRequest;
import com.example.demo.services.dtos.requests.auth.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    String login(LoginRequest request);
}
