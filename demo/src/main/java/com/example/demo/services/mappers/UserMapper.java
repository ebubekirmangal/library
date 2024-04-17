package com.example.demo.services.mappers;

import com.example.demo.entities.Borrow;
import com.example.demo.entities.User;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.AddUserResponse;
import com.example.demo.services.dtos.responses.user.DeleteUserResponse;
import com.example.demo.services.dtos.responses.user.GetAllUserResponse;
import com.example.demo.services.dtos.responses.user.UpdateUserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userToAddUserRequest(AddUserRequest request);
    @Mapping(target = "bookId",source = "borrows")
    AddUserResponse addUserResponseToUser(User user);

    User userToUpdateUserRequest(UpdateUserRequest request);
    @Mapping(target = "bookId",source = "borrows")
    UpdateUserResponse updateUserResponseToUser(User user);
    @Mapping(target = "bookId",source = "borrows")
    DeleteUserResponse deleteUserResponseToUser(User user);
    @Mapping(target = "bookId",source = "borrows")
    GetAllUserResponse getAllUserResponseToUser(User user);
    default List<Integer> mapBorrowsToBookId(List<Borrow> borrows) {
        if (borrows == null) {
            return null;
        }
        return borrows.stream()
                .map(borrow -> borrow.getId())
                .collect(Collectors.toList());
    }
}
