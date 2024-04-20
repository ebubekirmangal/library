package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.repositories.DeliveryRepository;
import com.example.demo.services.abstracts.BorrowService;
import com.example.demo.services.abstracts.DeliveryService;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.borrow.AddBorrowResponse;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.DeleteDeliveryResponse;
import com.example.demo.services.dtos.responses.delivery.GetAllDeliveryResponse;
import com.example.demo.services.mappers.BorrowMapper;
import com.example.demo.services.mappers.DeliveryMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private DeliveryRepository deliveryRepository;

    private BorrowService borrowService;


    public DeliveryServiceImpl(DeliveryRepository deliveryRepository, BorrowService borrowService) {
        this.deliveryRepository = deliveryRepository;
        this.borrowService = borrowService;
    }

    public AddDeliveryResponse add(AddDeliveryRequest request) {
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryToAddDeliveryRequest(request);

        int borrowId = request.getBorrowId();
        Borrow borrow = borrowService.findById(borrowId);


        borrow.setDeadLine(borrow.getPickUpDate().plusDays(21));
        delivery.setBorrow(borrow);
        delivery.setPenaltyFee(5);

        Book book = borrow.getBook();
        book.setIsBorrow(false);
        delivery.setBook(book);

        dateController(borrow,delivery);
        calculator(borrow,delivery);
        try {
            Delivery saved = deliveryRepository.save(delivery);
            AddDeliveryResponse response = DeliveryMapper.INSTANCE.addDeliveryResponseToDelivery(saved);
            return response;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Bu kitap zaten teslim edilmiştir.");
        }
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
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(()->new BusinessException("Id bulunamadı."));
        deliveryRepository.delete(delivery);
        DeleteDeliveryResponse response = DeliveryMapper.INSTANCE.deleteDeliverResponseToDelivery(delivery);
        return response;
    }
    @Override
    public void calculator(Borrow borrow, Delivery delivery) {
        long daysDifference = ChronoUnit.DAYS.between(borrow.getDeadLine(),delivery.getReceivedDate());
        if (daysDifference > 0) {
            delivery.setDelayDay(daysDifference);
            delivery.setTotalFee((int) daysDifference * delivery.getPenaltyFee());
            delivery.setMessage("Borcunuz bulunmaktadır.");
        } else {
            delivery.setDelayDay(0);
            delivery.setTotalFee((double) 0);
            delivery.setMessage("İşleminiz tamamlanmıştır.");
        }
    }
    @Override
    public void dateController(Borrow borrow, Delivery delivery) {
        if(borrow == null || delivery == null) {
            throw new BusinessException("Ödünç alma ya da teslim tarihleri girilmemiştir.");
        }
        LocalDate borrowDate = borrow.getPickUpDate();
        LocalDate deliveryDate = delivery.getReceivedDate();
        if(borrowDate.isAfter(deliveryDate)) {
            throw new BusinessException("Ödünç alma tarihi, teslim etme tarihinden büyük olamaz.");
        }
    }

}
