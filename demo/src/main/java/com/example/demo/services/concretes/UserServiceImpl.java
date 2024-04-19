package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Delivery;
import com.example.demo.entities.User;
import com.example.demo.services.dtos.responses.user.*;
import com.example.demo.services.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
//TODO: 1/userda delivery işlemi yapıldıktan sonra userdaki book id kaybolcak 2/ isBorrow otomatik değişcek.
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
    public DeleteUserResponse delete(String tcNum) {
        User user = tcIsPresentTcNum(tcNum);
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

    @Override
    public GetByTcNumUserResponse getByTcNum(String tcNum) {

        User user = userRepository.findByTcNum(tcNum);
        if(tcNum.equals("")){
            throw new BusinessException("Tc numarası bulunamıyor.");
        }

        List<Delivery> deliveries = user.getDeliveries();
        if (!deliveries.isEmpty()) {
            Delivery lastDelivery = deliveries.get(deliveries.size() - 1);
            Double totalFee = lastDelivery.getTotalFee();
            if(){ //TODO:ödün alınmış ama teslim edilmemiş
                //TODO:
            }else{
                if (totalFee == 0){
                    //TODO action true
                }else{
                    //TODO action false
                }
            }



        } else {
          //TODO: actio
        }


        User saved = userRepository.save(user);

        GetByTcNumUserResponse response = UserMapper.INSTANCE.getByTcNumUserResponseToUser(saved);
        return response;
    }

    private User tcIsPresentTcNum(String request){
        User userId = userRepository.findByTcNum(request);
        if(userId == null){
            throw new BusinessException("Tc numarası bulunamadı bulunamadı.");
        }
        return userId;
    }

    @Override
    public User findByTcNum(String tcNum) {
        if(tcNum.equals("")){
            throw  new BusinessException("Tc numarası bulunamadı");
        }

        return userRepository.findByTcNum(tcNum);

    }
}
