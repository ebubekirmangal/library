package com.example.demo.services.abstracts;

public interface PunishmentManagerService {

    AddPunishmentResponse add(AddPunishmentRequest request);

    List<GetAllPunishmentResponse> getAll();
}
