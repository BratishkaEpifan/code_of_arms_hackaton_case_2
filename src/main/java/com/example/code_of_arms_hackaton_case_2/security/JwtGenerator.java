package com.example.code_of_arms_hackaton_case_2.security;


import io.jsonwebtoken.*;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Log
public class JwtGenerator {

    private String jwtSecret = "celkoviy";

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            if (token != null) {
                Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
                return true;
            }
        } catch (ExpiredJwtException expiredJwtException) {
            log.severe("Token is expired");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            log.severe("Unsupported token");
        } catch (MalformedJwtException malformedJwtException) {
            log.severe("Malformed token");
        } catch (Exception e) {
            log.info(e.getMessage());
            log.severe("Invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}