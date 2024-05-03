package com.example.demo.services.concretes;

import com.example.demo.core.services.JwtService;
import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.AuthService;
import com.example.demo.services.dtos.requests.user.UserLoginRequest;
import com.example.demo.services.dtos.requests.user.UserRegisterRequest;
import com.example.demo.services.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public void register(UserRegisterRequest request) {//TODO:isAction take aktif hale getir. totalFee ve deadline ekle
        List<User> users = userRepository.findAll();

        for(User user:users){
            if(request.getTcNum().equals(user.getTcNum())){
                throw new BusinessException("Aynı Tc numarası ile zaten giriş yapılmıştır.");
            }
        }

        User user = UserMapper.INSTANCE.userToUserRegisterRequest(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        User saved = userRepository.save(user);

    }

    public String login(UserLoginRequest request){
        User user =
                userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new BusinessException("E-posta ya da şifre yanlış"));

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
                );
        if(!authentication.isAuthenticated())
            throw new BusinessException("E-posta ya da şifre yanlış.") ;


        Map<String,Object> extraClaims = new HashMap<>();
        extraClaims.put("tcNum",user.getTcNum());
        extraClaims.put("firstName",user.getFirstName());
        extraClaims.put("lastName",user.getLastName());

        return jwtService.generateToken(user.getUsername(), extraClaims);
    }
}
