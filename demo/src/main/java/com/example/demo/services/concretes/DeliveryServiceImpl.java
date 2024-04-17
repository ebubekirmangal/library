package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.repositories.BorrowRepository;
import com.example.demo.repositories.DeliveryRepository;
import com.example.demo.services.abstracts.DeliveryService;
import com.example.demo.services.dtos.requests.delivery.AddDeliveryRequest;
import com.example.demo.services.dtos.responses.delivery.AddDeliveryResponse;
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

    @Override
    public AddDeliveryResponse add(AddDeliveryRequest request) {
        // Borrow varlığını bul
        Optional<Borrow> optionalBorrow = borrowRepository.findById(request.getBorrowId());
        Borrow borrow = optionalBorrow.orElseThrow(() -> new BusinessException("Ödünç alma işlemi bulunamadı."));

        // Delivery varlığını oluştur ve Borrow varlığını ilişkilendir
        Delivery delivery = DeliveryMapper.INSTANCE.deliveryToAddDeliveryRequest(request);
        delivery.setBorrow(borrow);

        // Borrow kaydından pickUpDate değerini al ve deadLine değerini hesapla
        LocalDate pickUpDate = borrow.getPickUpDate();
        LocalDate deadline = pickUpDate.plusDays(15);
        borrow.setDeadLine(deadline);

        // Ceza hesapla ve uygula
        calculateAndApplyPunishment(borrow, delivery);

        // Delivery varlığını kaydet
        Delivery savedDelivery = deliveryRepository.save(delivery);

        // Response oluştur ve geri döndür
        AddDeliveryResponse response = DeliveryMapper.INSTANCE.addDeliveryResponseToDelivery(savedDelivery);
        return response;
    }

    public void calculateAndApplyPunishment(Borrow borrow, Delivery delivery) {
        LocalDate receivedDate = delivery.getReceivedDate();
        LocalDate pickUpDate = borrow.getPickUpDate();
        LocalDate deadline = borrow.getPickUpDate().plusDays(15);

        long daysDifference = ChronoUnit.DAYS.between(deadline, receivedDate);
        // Gecikme süresi negatifse, yani teslim tarihi son teslim tarihinden önceyse,
        // bunu pozitif bir değere çevirerek delayDate'e atayın
        if (daysDifference < 0) {
            daysDifference = Math.abs(daysDifference);
            delivery.setDelayDay(daysDifference);

            // Gecikme varsa ceza hesapla ve uygula
            double penaltyFee = calculatePenaltyFee(daysDifference);
            delivery.setTotalFee(penaltyFee);
        } else {
            // Gecikme yoksa delayDate değerini 0 olarak ayarlayabilirsiniz
            delivery.setDelayDay(0);
        }
    }

    private double calculatePenaltyFee(long daysDifference) {
        // Her bir gecikme günü için belirli bir ücret hesaplayabilirsiniz
        // Örneğin, her bir gecikme günü için 5 birimlik bir ücret
        return daysDifference * 5;
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
