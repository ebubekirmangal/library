//package com.example.demo.services.mappers;
//
//import com.example.demo.entities.Punishment;
//import com.example.demo.services.dtos.responses.punishment.GetAllPunishmentResponse;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper
//public interface PunishmentManagerMapper {
//
//    PunishmentManagerMapper INSTANCE = Mappers.getMapper(PunishmentManagerMapper.class);
//    @Mapping(target = "tcNum", source = "borrow.user.tcNum")
//    @Mapping(target = "firstName", source = "borrow.user.firstName")
//    @Mapping(target = "lastName", source = "borrow.user.lastName")
//    @Mapping(target = "bookName", source = "borrow.book.name")
//    @Mapping(target = "deadLine", source = "borrow.deadLine")
//    @Mapping(target = "receivedDate",source = "delivery.receivedDate")
//    @Mapping(target = "pickUpDate",source = "borrow.pickUpDate")
//    GetAllPunishmentResponse getAllPunishmentResponseToPunishment(Punishment punishment);
//}
