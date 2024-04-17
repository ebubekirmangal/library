package com.example.demo.services.concretes;

import com.example.demo.services.abstracts.PunishmentManagerService;
import com.example.demo.services.dtos.requests.punishment.AddPunishmentRequest;
import com.example.demo.services.dtos.responses.punishment.AddPunishmentResponse;
import com.example.demo.services.dtos.responses.punishment.GetAllPunishmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PunishmentManagerServiceImpl implements PunishmentManagerService {
    @Override
    public AddPunishmentResponse add(AddPunishmentRequest request) {
        return null;
    }

    @Override
    public List<GetAllPunishmentResponse> getAll() {
        return null;
    }
}
