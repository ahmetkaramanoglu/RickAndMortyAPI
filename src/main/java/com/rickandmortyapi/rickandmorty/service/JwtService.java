package com.rickandmortyapi.rickandmorty.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//Burda token ile ilgili islemler yapiliyor. Token olusturuluyor, tokenin gecerliligi kontrol ediliyor, tokenin icindeki bilgiler aliniyor.
@Service
public class JwtService {
    @Value("${spring.jwt.key}")
    private String SECRET;

    //Tokenin icindeki bilgileri almak icin
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    //Tokenin gecerlilik suresini almak icin
    private Date extractExpiration(String token){
        Claims claims = extractAllClaims(token);
        return claims.getExpiration();
    }
    //Tokendaki user bilgisini almak icin
    public String extractUser(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }
    //Tokenin gecerli olup olmadigini kontrol etmek icin
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUser(token);
        final Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
    }

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("deneme", "deneme");
        return createToken(claims, userName);
    }

    //Burda 2 dklik bir token olusturuluyor.
    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 2))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();

    }
    //
    private Key getSignKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
