//package com.example.demo.controllers;
//
//import com.example.demo.services.abstracts.PunishmentManagerService;
//import com.example.demo.services.dtos.requests.punishment.AddPunishmentRequest;
//import com.example.demo.services.dtos.responses.punishment.AddPunishmentResponse;
//import com.example.demo.services.dtos.responses.punishment.GetAllPunishmentResponse;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/punishmentManagement")
//@AllArgsConstructor
//public class PunishmentManagementController {
//
//    private PunishmentManagerService punishmentManagerService;
//
//
//    @PostMapping("/add")
//    @ResponseStatus(HttpStatus.CREATED)
//    public AddPunishmentResponse add(AddPunishmentRequest request){
//        return punishmentManagerService.add(request);
//    }
//
//    @GetMapping("/getAll")
//    public List<GetAllPunishmentResponse> getAll(){
//        return punishmentManagerService.getAll();
//    }
//
//
//}
