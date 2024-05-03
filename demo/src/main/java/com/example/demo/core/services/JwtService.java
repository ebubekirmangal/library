package com.example.demo.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {
    @Value("${jwt.expiration}")
    private long EXPIRATION;
    @Value("${jwt.key}")
    private String Secret_Key;


    public String generateToken(String email, Map<String,Object> extraClaims){
      return   Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .subject(email)
                .claims(extraClaims)
                .signWith(getSecretKey())
                .compact();
    }

    public Boolean validateToken(String token){
        Date expirationDate = getClaimsFromToken(token).getExpiration();
        return  expirationDate.after(new Date());
    }
    public String extractEmail(String token){
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token){
        return Jwts.parser()
                .verifyWith((SecretKey) getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getBody();
    }
    private Key getSecretKey(){
        byte[] keyByte = Decoders.BASE64URL.decode(Secret_Key);
        return Keys.hmacShaKeyFor(keyByte);
    }
}
