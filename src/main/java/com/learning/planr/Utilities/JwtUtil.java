package com.learning.planr.Utilities;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecretKey;

//    public JwtUtil() {
//        try {
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
//            SecretKey sk= keyGenerator.generateKey();
//            jwtSecretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//    }
    public String generateToken(String username) {
            Map<String, Object> claims=new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 60 * 60 * 30))
                .and()
                .signWith(getSigningkey())
                .compact();

    }
    public SecretKey getSigningkey(){
        byte[] jwtSecretBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(jwtSecretBytes);
    }
    
    public String getUsername(String token){
        Claims claims= extractAllClaims(token);
        return claims.getSubject();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningkey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token){
        Claims claims= extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public boolean validateToken (String token, UserDetails userDetails) {
        String username= getUsername(token);

        return username.equals(userDetails.getUsername());
    }


}
