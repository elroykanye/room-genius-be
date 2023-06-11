package com.sharonghranui.roomgenius.business.service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

public class JwtUtil {
    private static final String secret = "OANDINOEPkbpuceconoeun&onaosnd93N)8jNOA*ONASOD*N#OIN*#NOD#NOI#IODoinO#ND";
    private static final String issuer = "room_genius";
    private static final long expiration = 60 * 60 * 24;

    public static String build(String username){
        return Jwts.builder().setIssuer(issuer)
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plusSeconds(expiration)))
                .signWith(secretKey())
                .compact();

    }
    public static String username(String token){
        return parse(token)
                .getBody()
                .getSubject();
    }
    private static Jws<Claims> parse(String token){
        return Jwts.parserBuilder()
                .requireIssuer(issuer)
                .setSigningKey(secretKey())
                .build()
                .parseClaimsJws(token);
    }
    public static SecretKey secretKey(){
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

}

