package com.example.demo.mappers;

import com.example.demo.entities.User;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.AddUserResponse;
import com.example.demo.services.dtos.responses.user.DeleteUserResponse;
import com.example.demo.services.dtos.responses.user.GetAllUserResponse;
import com.example.demo.services.dtos.responses.user.UpdateUserResponse;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userToAddUserRequest(AddUserRequest request);

    AddUserResponse addUserResponseToUser(User user);

    User userToUpdateUserRequest(UpdateUserRequest request);

    UpdateUserResponse updateUserResponseToUser(User user);

    DeleteUserResponse deleteUserResponseToUser(User user);

    GetAllUserResponse getAllUserResponseToUser(User user);
}
