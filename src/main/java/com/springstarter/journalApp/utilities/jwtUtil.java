package com.springstarter.journalApp.utilities;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class jwtUtil {

    private String SECRET_KEY="TaK+HaV^uvCHEFsEVfypW#79g9^k*Z9$V";

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String extractUserName(String token){
        return extractAllClaims(token).getSubject();
    }

    public Date extractExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }
    public String extractClaims(String token,String claim){
        return extractAllClaims(token).get(claim,String.class);
    }
    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String userName){
        Map<String ,Object> claims=new HashMap<>();
        return createToken(claims,userName);
    }

    private String createToken(Map<String ,Object> claims,String subject){
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .header().empty().add("type","JWT")
                .and()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(getSigningKey())
                .compact();

    }

    public Boolean validateToken(String token,String userName){
        final String extractedUserName=extractUserName(token);
        return (extractedUserName.equals(userName)&&!isTokenExpired(token));
    }
}
