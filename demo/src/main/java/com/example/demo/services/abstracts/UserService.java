package com.example.demo.services.abstracts;

import com.example.demo.entities.User;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.*;

import java.util.List;

public interface UserService {
    AddUserResponse add(AddUserRequest request);

    UpdateUserResponse update(UpdateUserRequest request);

    DeleteUserResponse delete(String tcNum);

    List<GetAllUserResponse> getAll();

    GetByTcNumUserResponse getByTcNum(String tcNum);

    User findByTcNum(String tcNum);
}
