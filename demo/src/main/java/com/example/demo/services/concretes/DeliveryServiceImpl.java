package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.repositories.DeliveryRepository;
import com.example.demo.services.abstracts.DeliveryService;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.DeleteDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import com.example.demo.services.mappers.DeliveryMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    private BorrowRepository borrowRepository;


    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, BorrowRepository borrowRepository) {
        this.deliveryRepository = deliveryRepository;
        this.borrowRepository = borrowRepository;
    }

    public AddDeliveryResponse add(AddDeliveryRequest request) {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryToAddDeliveryRequest(request);

        int borrowId = request.getBorrowId();
        Borrow borrow = borrowRepository.findById(borrowId).orElseThrow(() -> new BusinessException("id bulunamadı."));
        borrow.setDeadLine(borrow.getPickUpDate().plusDays(15));
        delivery.setBorrow(borrow);
        delivery.setPenaltyFee(5);

        long daysDifference =ChronoUnit.DAYS.between(borrow.getDeadLine(),delivery.getReceivedDate());
        delivery.setDelayDay(daysDifference);

        if (daysDifference > 0) {
            delivery.setDelayDay(daysDifference);
            delivery.setTotalFee((int) daysDifference * delivery.getPenaltyFee());
            delivery.setMessage("Borcunuz bulunmaktadır.");
        } else {
            delivery.setDelayDay(0);
            delivery.setTotalFee(0);
            delivery.setMessage("İşleminiz tamamlanmıştır.");
        }


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

    @Override
    public DeleteDeliveryResponse delete(int id) {
        Delivery delivery = deliveryRepository.getById(id);
        if(delivery == null){
            new BusinessException("Id bulunamadı.");
        }
        deliveryRepository.delete(delivery);

        DeleteDeliveryResponse response = DeliveryMapper.INSTANCE.deleteDeliverResponseToDelivery(delivery);

        return response;

    }
}
