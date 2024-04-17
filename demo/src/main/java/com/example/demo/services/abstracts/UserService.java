package com.example.demo.services.abstracts;

import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.DeleteUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.AddUserResponse;
import com.example.demo.services.dtos.responses.user.DeleteUserResponse;
import com.example.demo.services.dtos.responses.user.GetAllUserResponse;
import com.example.demo.services.dtos.responses.user.UpdateUserResponse;

import java.util.List;

public interface UserService {
    AddUserResponse add(AddUserRequest request);

    UpdateUserResponse update(UpdateUserRequest request);

    DeleteUserResponse delete(DeleteUserRequest request);

    List<GetAllUserResponse> getAll();
}
