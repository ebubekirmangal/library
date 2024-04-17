package com.example.demo.services.concretes;

import com.example.demo.entities.Delivery;
import com.example.demo.repositories.DeliveryRepository;
import com.example.demo.services.abstracts.DeliveryService;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import com.example.demo.services.mappers.DeliveryMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public AddDeliveryResponse add(AddDeliveryRequest request) {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryToAddDeliveryRequest(request);
        Delivery saved = deliveryRepository.save(delivery);

        AddDeliveryResponse response = DeliveryMapper.INSTANCE.addDeliveryResponseToDelivery(saved);
        return response;
    }

    @Override
    public List<GetAllDeliveryResponse> getAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();
        List<GetAllDeliveryResponse> result = new ArrayList<>();

        for(Delivery delivery:deliveries){
            GetAllDeliveryResponse dto = DeliveryMapper.INSTANCE.getAllDeliveryResponseToDelivery(delivery);
            result.add(dto);
        }

        return result;
    }
}
