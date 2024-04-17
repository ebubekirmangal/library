package com.example.demo.services.abstracts;

import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;

import java.util.List;

public interface DeliveryService {

    AddDeliveryResponse add(AddDeliveryRequest request);

    List<GetAllDeliveryResponse> getAll();
}