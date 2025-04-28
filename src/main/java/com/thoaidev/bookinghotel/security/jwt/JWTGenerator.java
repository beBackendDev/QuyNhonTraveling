package com.thoaidev.bookinghotel.security.jwt;

import java.util.Date;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {
    public String generateToken (Authentication authentication){
        System.out.println("this JWTGenerator");
        String username =authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + SecurityConstant.JWT_EXPIRATION);

        String token = Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(expireDate)
            .signWith(SecurityConstant.JWT_SECRET, SignatureAlgorithm.HS512)
            .compact();
            System.out.println("New token :");
            System.out.println(token);
        return token;
    }

    public String  getUsernameFromJWT  (String token){
        Claims claims = Jwts.parserBuilder()
            .setSigningKey(SecurityConstant.JWT_SECRET)
            .build()
            .parseClaimsJws(token)
            .getBody();
    return claims.getSubject();
    }

    public boolean validateToken (String token){
        try{
            Jwts.parserBuilder()
            .setSigningKey(SecurityConstant.JWT_SECRET)
            .build()
            .parseClaimsJws(token);
            return true;
        }catch (Exception ex){
            throw new AuthenticationCredentialsNotFoundException("Mã Token đã tồi tại hoặc không hợp lệ");

        }
    }
}
