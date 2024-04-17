package com.example.demo.services.mappers;

import com.example.demo.entities.Delivery;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);
    @Mapping(target = "book.id", source = "bookId")
    @Mapping(target = "user.tcNum", source = "tcNum")
    Delivery deliveryToAddDeliveryRequest(AddDeliveryRequest request);
    @Mapping(target = "bookName",source = "book.name")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    AddDeliveryResponse addDeliveryResponseToDelivery(Delivery delivery);
    @Mapping(target = "bookName",source = "book.name")
    @Mapping(target = "firstName",source = "user.firstName")
    @Mapping(target = "lastName",source = "user.lastName")
    GetAllDeliveryResponse getAllDeliveryResponseToDelivery(Delivery delivery);
}
