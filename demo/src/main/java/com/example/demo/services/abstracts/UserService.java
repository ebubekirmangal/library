package com.example.demo.services.abstracts;

import com.example.demo.entities.User;
import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;
import com.example.demo.services.dtos.responses.user.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {

    //UpdateUserResponse update(UpdateUserRequest request);

    DeleteUserResponse delete(String tcNum);

    List<GetAllUserResponse> getAll();

    GetByTcNumUserResponse getByTcNum(String tcNum);

    User findByTcNum(String tcNum);

}
