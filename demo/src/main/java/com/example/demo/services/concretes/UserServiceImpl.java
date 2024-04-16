package com.example.demo.services.concretes;

import com.example.demo.entities.User;
import com.example.demo.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.DeleteUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.AddUserResponse;
import com.example.demo.services.dtos.responses.user.DeleteUserResponse;
import com.example.demo.services.dtos.responses.user.GetAllUserResponse;
import com.example.demo.services.dtos.responses.user.UpdateUserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AddUserResponse add(AddUserRequest request) {
        User user = UserMapper.INSTANCE.userToAddUserRequest(request);
        User saved = userRepository.save(user);

        AddUserResponse response = UserMapper.INSTANCE.addUserResponseToUser(saved);

        return response;
    }

    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {


        return null;
    }

    @Override
    public DeleteUserResponse delete(DeleteUserRequest request) {


        return null;
    }

    @Override
    public GetAllUserResponse getAll() {


        return null;
    }
}
