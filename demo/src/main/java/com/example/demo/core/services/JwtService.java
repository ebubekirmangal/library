package com.example.demo.core.services;

import com.example.demo.core.filters.JwtFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${jwt.Expiration}")
    private long EXPIRATION;
    @Value("${jwt.SecretKey}")
    private String Secret_Key;

    public String generateToken(String email, Map<String,Object> extraClaims){

        return    Jwts
                .builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .claims(extraClaims)
                .subject(email)
                .signWith(getSignKey())
                .compact();

    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64URL.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public Boolean validateToken(String token)
    {
        return getClaimsFromToken(token).getExpiration().after(new Date());
    }
    public String extractUsername(String token)
    {
        return getClaimsFromToken(token).getSubject();
    }

    public Claims getClaimsFromToken(String token){
        SecretKey key = (SecretKey) getSignKey();
        return Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
