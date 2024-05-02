package com.example.demo.services.concretes;

import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Book;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.entities.User;
import com.example.demo.repositories.DeliveryRepository;
import com.example.demo.services.dtos.responses.user.*;
import com.example.demo.services.mappers.UserMapper;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.AddUserRequest;
import com.example.demo.services.dtos.requests.user.UpdateUserRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public AddUserResponse add(AddUserRequest request) {
        List<User> users = userRepository.findAll();

        for(User user:users){
            if(request.getTcNum().equals(user.getTcNum())){
                throw new BusinessException("Aynı Tc numarası ile zaten giriş yapılmıştır.");
            }
        }

        User user = UserMapper.INSTANCE.userToAddUserRequest(request);

        User saved = userRepository.save(user);

        AddUserResponse response = UserMapper.INSTANCE.addUserResponseToUser(saved);

        return response;
    }
    @Override
    public UpdateUserResponse update(UpdateUserRequest request) {
        User user = UserMapper.INSTANCE.userToUpdateUserRequest(request);
        User updated = userRepository.save(user);

        UpdateUserResponse response = UserMapper.INSTANCE.updateUserResponseToUser(updated);
        return response;
    }
    @Override
    public DeleteUserResponse delete(String tcNum) {
        User user = tcIsPresentTcNum(tcNum);
        userRepository.delete(user);
        DeleteUserResponse response = UserMapper.INSTANCE.deleteUserResponseToUser(user);
        return response;
    }
    @Override
    public List<GetAllUserResponse> getAll() {
        List<User> users = userRepository.findAll();
        List<GetAllUserResponse> result = new ArrayList<>();

        for(User user:users){
            GetAllUserResponse dto = UserMapper.INSTANCE.getAllUserResponseToUser(user);
            result.add(dto);
        }

        return result;
    }
    @Override
    public GetByTcNumUserResponse getByTcNum(String tcNum) {
        User user = userRepository.findByTcNum(tcNum);

        if (tcNum.equals("")) {
            throw new BusinessException("Tc numarası bulunamıyor.");
        }

        updateIsActionTake(user);

        User saved = userRepository.save(user);

        return UserMapper.INSTANCE.getByTcNumUserResponseToUser(saved);
    }
    private void updateIsActionTake(User user) {
        List<Borrow> borrows = user.getBorrows();
        List<Delivery> deliveries = user.getDeliveries();




        // Eğer kullanıcıya ait teslimatlar varsa
        if (deliveries != null && !deliveries.isEmpty()) {
            // En son teslimatı bul
            Delivery lastDelivery = deliveries.stream()
                    .max(Comparator.comparingLong(Delivery::getId))
                    .orElseThrow(() -> new BusinessException("Teslim işlemi hiç yapılmamış."));
            // Son teslimatın toplam ücreti 0'dan farklı ise isActionTake'i false yap
            if(lastDelivery.getTotalFee() != 0)
            user.setIsActionTake(false);
        }
        // Eğer kullanıcıya ait borçlar varsa
        else if (borrows != null && !borrows.isEmpty()) {

            // Her bir borç için kontrol yap
            for (Borrow borrow : borrows) {
                List<Book> books = borrow.getBooks(); // Borrow sınıfında List<Book> tipinde tanımlandığı için doğrudan bu listeyi alabiliriz
                for (Book book : books) {
                    LocalDate deadLine = borrow.getPickUpDate().plusDays(21);
                    // Borcun son teslim tarihi bugünden önceyse isActionTake'i true yap
                    if (deadLine != null && deadLine.isAfter(LocalDate.now())) {
                        user.setIsActionTake(true);
                        // Herhangi bir borç günü geçtiyse döngüyü sonlandır
                        return;
                    }
                }
            }
            // Tüm borçlar günü geçmemişse isActionTake'i false yap
            user.setIsActionTake(false);
        } else {
            // Kullanıcıya ait ne teslimat ne de borç varsa isActionTake'i true yap
            user.setIsActionTake(true);
        }
    }
    private User tcIsPresentTcNum(String request){
        User userId = userRepository.findByTcNum(request);
        if(userId == null){
            throw new BusinessException("Tc numarası bulunamadı.");
        }
        return userId;
    }
    @Override
    public User findByTcNum(String tcNum) {
        if(tcNum.equals("")){
            throw  new BusinessException("Tc numarası bulunamadı");
        }
        return userRepository.findByTcNum(tcNum);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow();
    }
}
