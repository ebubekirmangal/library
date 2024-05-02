package com.example.demo.controllers;

import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/control/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserResponse add(@RequestBody @Valid AddUserRequest request){
        return userService.add(request);
    }

    @PutMapping("/update")
    public UpdateUserResponse update(@RequestBody UpdateUserRequest request){
        return userService.update(request);
    }

    @DeleteMapping("/delete/{tcNum}")
    public DeleteUserResponse delete(@PathVariable("tcNum") String tcNum){
        return userService.delete(tcNum);
    }

    @GetMapping("/getAll")
    public List<GetAllUserResponse> getAll(){
        return userService.getAll();
    }

    @GetMapping("/isActionTake/{tcNum}")
    public GetByTcNumUserResponse getByTcNum(@PathVariable("tcNum")  String tcNum ){
        return userService.getByTcNum(tcNum);
    }
}
