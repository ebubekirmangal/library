package com.example.demo.controllers;

import com.example.demo.services.abstracts.DeliveryService;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.DeleteDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private DeliveryService deliveryService;

    public DeliveryController(@RequestBody DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public AddDeliveryResponse add(@RequestBody AddDeliveryRequest request){
        return deliveryService.add(request);
    }

    @GetMapping("/getAll")
    public List<GetAllDeliveryResponse> getAll(){
        return deliveryService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public DeleteDeliveryResponse delete(@PathVariable("id") int id){
       return deliveryService.delete(id);
    }
}
