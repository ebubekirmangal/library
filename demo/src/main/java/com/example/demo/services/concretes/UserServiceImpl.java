package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
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

import java.util.ArrayList;
import java.util.List;

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
        User user = UserMapper.INSTANCE.userToUpdateUserRequest(request);

        User updated = userRepository.save(user);

        UpdateUserResponse response = UserMapper.INSTANCE.updateUserResponseToUser(updated);

        return response;
    }

    @Override
    public DeleteUserResponse delete(DeleteUserRequest request) {
        User user = tcIsPresentTcNum(request.getTcNum());
        userRepository.delete(user);
       DeleteUserResponse response = UserMapper.INSTANCE.deleteUserResponseToUser(user);
        return response;
    }

    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> result = new ArrayList<>();

        for(User user:users){
            GetAllUserResponse dto = UserMapper.INSTANCE.getAllUserResponseToUser(user);
            result.add(dto);
        }

        return result;
    }

    private User tcIsPresentTcNum(String request){
        User userId = userRepository.findByTcNum(request);
        if(userId == null){
            throw new BusinessException("Tc numarası bulunamadı bulunamadı.");
        }
        return userId;
    }
}
