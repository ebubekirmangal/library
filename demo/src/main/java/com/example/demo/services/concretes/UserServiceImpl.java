package com.example.demo.services.concretes;

import com.example.demo.core.services.JwtService;
import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.Borrow;
import com.example.demo.entities.Delivery;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.UserService;
import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;
import com.example.demo.services.dtos.responses.user.DeleteUserResponse;
import com.example.demo.services.dtos.responses.user.GetAllUserResponse;
import com.example.demo.services.dtos.responses.user.GetByTcNumUserResponse;
import com.example.demo.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(()-> new BusinessException("E-posta bulunamadı."));
    }

//    @Override
//    public UpdateUserResponse update(UpdateUserRequest request) {
//        User user = UserMapper.INSTANCE.userToUpdateUserRequest(request);
//        User updated = userRepository.save(user);
//
//        UpdateUserResponse response = UserMapper.INSTANCE.updateUserResponseToUser(updated);
//        return response;
//    }
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
        // Eğer kullanıcıya ait teslimatlar varsa
        if (user.getDeliveries() != null && !user.getDeliveries().isEmpty()) {
            // En son teslimatı bul
            Delivery lastDelivery = user.getDeliveries().stream()
                    .max(Comparator.comparingLong(Delivery::getId))
                    .orElseThrow(() -> new BusinessException("Teslim işlemi hiç yapılmamış."));

            // Son teslimatın toplam ücreti 0'dan farklı ise isActionTake'i false yap
            user.setIsActionTake(lastDelivery.getTotalFee() == 0);
        } else {
            List<Borrow> borrows = user.getBorrows();
            // Eğer kullanıcıya ait borçlar varsa
            if (borrows != null && !borrows.isEmpty()) {
                // Her bir borç için kontrol yap
                for (Borrow borrow : borrows) {
                    // Borcun son teslim tarihi bugünden önceyse isActionTake'i true yap
                    if (borrow.getDeadLine().isBefore(LocalDate.now())) {
                        user.setIsActionTake(true);
                        // Herhangi bir borç günü geçtiyse döngüyü sonlandır
                        return;
                    }
                }
                // Tüm borçlar günü geçmemişse isActionTake'i false yap
                user.setIsActionTake(false);
            } else {
                // Kullanıcıya ait ne teslimat ne de borç varsa isActionTake'i true yap
                user.setIsActionTake(true);
            }
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


}
