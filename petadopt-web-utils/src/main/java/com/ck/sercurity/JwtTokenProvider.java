package com.ck.sercurity;

import com.ck.dto.CustomUserDetails;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component

public class JwtTokenProvider {
    private final String JWT_SECRET = "lolooooooooooooooo";
    private final long JWT_EXPIRATION = 604800000L;
    Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);
    public String generateToken(CustomUserDetails customUserDetails){
        Date date = new Date();
        Date expiryDate = new Date(date.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setSubject(customUserDetails.getUser().getUserName())
                .setIssuedAt(date)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }
    public String getUserNameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
