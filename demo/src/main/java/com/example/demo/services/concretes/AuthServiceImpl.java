package com.example.demo.services.concretes;

import com.example.demo.core.services.JwtService;
import com.example.demo.core.utils.exceptions.types.BusinessException;
import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.abstracts.AuthService;
import com.example.demo.services.dtos.requests.auth.LoginRequest;
import com.example.demo.services.dtos.requests.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;
    @Override
    public void register(RegisterRequest request) {
        User user = new User();
        user.setTcNum(request.getTcNum());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

    }

    @Override
    public String login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new BusinessException("E-posta ya da şifre hatalı."));

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated())
            throw new BusinessException("E-posta ya da şifre hatalı.");
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("tcNum",user.getTcNum());

        return jwtService.generateToken(user.getEmail(),extraClaims );

    }
}
