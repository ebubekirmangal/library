package com.example.demo.services.mappers;

import com.example.demo.entities.*;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import com.example.demo.services.dtos.responses.user.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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
        List<ListBooksPurshesedSoFar> dtos = new ArrayList<>();
        for (Borrow borrow : borrows) {
            ListBooksPurshesedSoFar dto = new ListBooksPurshesedSoFar();
            List<InfoLittleList> littleDtos = new ArrayList<>();
            List<Book> books = borrow.getBooks();
            InfoLittleList littleList = new InfoLittleList();
            borrow.setDeadLine(borrow.getPickUpDate().plusDays(21));
            for (Book book : books) {
                littleList.setBookName(book.getName());
                if (borrow.getDelivery() != null) {
                littleList.setBookStatus(BookStatus.OnTheShelf);
                littleList.setDeadLine(null);
                    dto.setTotalFee(borrow.getDelivery().getTotalFee());//TODO:totalFee yi direkt getByTcNumda döndür
                } else {
                littleList.setBookStatus(BookStatus.AtTheVisitor);
                littleList.setDeadLine(borrow.getPickUpDate().plusDays(21));
                    dto.setTotalFee(null);//TODO:totalFee yi direkt getByTcNumda döndür
                }
            }


            littleDtos.add(littleList);
            dto.setBookInfos(littleDtos);
            dtos.add(dto);
        }
        return dtos;
    }
}
