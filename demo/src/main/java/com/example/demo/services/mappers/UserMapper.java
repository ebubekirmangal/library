package com.example.demo.services.mappers;

import com.example.demo.entities.*;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userToAddUserRequest(AddUserRequest request);

    AddUserResponse addUserResponseToUser(User user);

    User userToUpdateUserRequest(UpdateUserRequest request);

    UpdateUserResponse updateUserResponseToUser(User user);

    DeleteUserResponse deleteUserResponseToUser(User user);
    @Mapping(target = "booksPurshasedSoFar",source = "borrows")
    GetAllUserResponse getAllUserResponseToUser(User user);
    @Mapping(target = "booksPurshasedSoFar",source = "borrows")
    GetByTcNumUserResponse getByTcNumUserResponseToUser(User user);

    default List<ListBooksPurshesedSoFar> mapBorrowsToBookId(List<Borrow> borrows) {
        if (borrows == null) {
            return null;
        }
        return borrows.stream()
                .map(borrow -> {
                    ListBooksPurshesedSoFar dto = new ListBooksPurshesedSoFar();
                    dto.setBookName(borrow.getBook().getName());
                    if (borrow.getDelivery() != null){
                        dto.setBookStatus(BookStatus.OnTheShelf);
                        dto.setTotalFee(borrow.getDelivery().getTotalFee());
                    } else {
                        dto.setBookStatus(BookStatus.AtTheVisitor);
                        dto.setDeadLine(borrow.getDeadLine());
                        dto.setTotalFee(0.0);
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
