package com.example.demo.controllers;

import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //@PutMapping("/update")
    //public UpdateUserResponse update(@RequestBody UpdateUserRequest request){
    //    return userService.update(request);
    //}

    @DeleteMapping("/delete/{tcNum}")
    public DeleteUserResponse delete(@PathVariable("tcNum") String tcNum){
        return userService.delete(tcNum);
    }

    @GetMapping("/getAll")
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/isActionTake/{tcNum}")
    public GetByTcNumUserResponse getByTcNum(@PathVariable("tcNum") String tcNum ){
        return userService.getByTcNum(tcNum);
    }
}
