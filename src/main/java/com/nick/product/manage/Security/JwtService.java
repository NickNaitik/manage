package com.nick.product.manage.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    //Use this - https://www.devglan.com/online-tools/hmac-sha256-online
    private static final String secretKey = "80a7da39b9502adda3b098483f267e293fe631ad9c9be482e5fc13f641d65cc7";

    public String extractUserId(String jwt) {
        return  extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("IN CLAIMS LINE 27");
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(
            UserDetails userDetails) {
        return generateToken( new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        System.out.println("AAYA GENERATE TOKEN PR ");
        return Jwts.builder().setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        System.out.println("Validating Token !");
        final String userId = extractUserId(token);
        return (userId.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {

        System.out.println("Checking Expiration!!");
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        System.out.println("Checking Expiration in method !!");
        return extractClaim(token, Claims::getExpiration);
    }
}
