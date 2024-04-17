//package com.example.demo.services.concretes;
//
//import com.example.demo.entities.Borrow;
//import com.example.demo.entities.Punishment;
//import com.example.demo.repositories.PunishmentManagerRepository;
//import com.example.demo.services.abstracts.PunishmentManagerService;
//import com.example.demo.services.dtos.requests.punishment.AddPunishmentRequest;
//import com.example.demo.services.dtos.responses.punishment.AddPunishmentResponse;
//import com.example.demo.services.dtos.responses.punishment.GetAllPunishmentResponse;
//import com.example.demo.services.mappers.PunishmentManagerMapper;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class PunishmentManagerServiceImpl implements PunishmentManagerService {
//
//    private PunishmentManagerRepository punishmentRepository;
//
//    public PunishmentManagerServiceImpl(PunishmentManagerRepository punishmentRepository) {
//        this.punishmentRepository = punishmentRepository;
//    }
//
//    @Override
//    public AddPunishmentResponse add(AddPunishmentRequest request) {
//        return null;
//    }
//
//    @Override
//    public List<GetAllPunishmentResponse> getAll() {
//        List<Punishment> punishments = punishmentRepository.findAll();
//        List<GetAllPunishmentResponse> result = new ArrayList<>();
//
//        for(Punishment punishment:punishments){
//            calculateAndApplyPunishment(punishment);
//            GetAllPunishmentResponse dto = PunishmentManagerMapper.INSTANCE.getAllPunishmentResponseToPunishment(punishment);
//            result.add(dto);
//        }
//
//        return result;
//    }
//
//    @Override
//    public String calculateAndApplyPunishment(Punishment punishment) {
//
//        LocalDate receivedDate = punishment.getDelivery().getReceivedDate();
//        LocalDate pickUpDate = punishment.getBorrow().getPickUpDate();
//        LocalDate deadline = punishment.getBorrow().getPickUpDate().plusDays(15);
//
//        long daysDifference = deadline.until(receivedDate).getDays();
//        if (daysDifference < 0) {
//            // Ceza uygula
//            double penaltyFee = calculatePenaltyFee(daysDifference);
//
//          return "ebubekir" ;
//        } else {
//        return    "yasin";
//        }
//
//    }
//
//    private double calculatePenaltyFee(long daysDifference) {
//        return daysDifference * 5;
//    }
//}
