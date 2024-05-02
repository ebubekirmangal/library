package com.example.demo.services.mappers;

import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.DeleteDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);
    @Mapping(target = "borrow.id", source = "borrowId")
    Delivery deliveryToAddDeliveryRequest(AddDeliveryRequest request);

      @Mapping(target = "tcNum", source = "borrow.user.tcNum")
      @Mapping(target = "firstName", source = "borrow.user.firstName")
      @Mapping(target = "lastName", source = "borrow.user.lastName")
      @Mapping(target = "deadLine", source = "borrow.deadLine")
      @Mapping(target = "pickUpDate",source = "borrow.pickUpDate")
      @Mapping(target = "delayDate", source = "delayDay")
      @Mapping(target = "totalFee", source = "totalFee")
      @Mapping(target = "message", source = "message")
    AddDeliveryResponse addDeliveryResponseToDelivery(Delivery delivery);
      @Mapping(target = "tcNum", source = "borrow.user.tcNum")
      @Mapping(target = "firstName", source = "borrow.user.firstName")
      @Mapping(target = "lastName", source = "borrow.user.lastName")
      @Mapping(target = "bookNames", source = "borrow.books",qualifiedByName = "mapToBookNames")
      @Mapping(target = "deadLine", source = "borrow.deadLine")
      @Mapping(target = "pickUpDate",source = "borrow.pickUpDate")
      @Mapping(target = "delayDate", source = "delayDay")
      @Mapping(target = "totalFee", source = "totalFee")
      @Mapping(target = "message", source = "message")
    GetAllDeliveryResponse getAllDeliveryResponseToDelivery(Delivery delivery);

      @Mapping(target = "tcNum", source = "borrow.user.tcNum")
      @Mapping(target = "firstName", source = "borrow.user.firstName")
      @Mapping(target = "lastName", source = "borrow.user.lastName")
      @Mapping(target = "bookNames", source = "borrow.books",qualifiedByName = "mapToBookNames")
      @Mapping(target = "deadLine", source = "borrow.deadLine")
      @Mapping(target = "pickUpDate",source = "borrow.pickUpDate")
      @Mapping(target = "delayDate", source = "delayDay")
      @Mapping(target = "totalFee", source = "totalFee")
      @Mapping(target = "message", source = "message")
    DeleteDeliveryResponse deleteDeliverResponseToDelivery(Delivery delivery);
    @Named("mapToBookNames")
    default List<String> mapToBookNames(List<Book> books) {
        return books.stream()
                .map(Book::getName)
                .collect(Collectors.toList());
    }
}
