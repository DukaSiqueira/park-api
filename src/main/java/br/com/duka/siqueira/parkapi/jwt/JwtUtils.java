package br.com.duka.siqueira.parkapi.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import static io.jsonwebtoken.lang.Strings.UTF_8;
import static java.time.ZoneId.systemDefault;

@Slf4j
public class JwtUtils {

     private static final String JWT_BEARER = "Bearer";
     private static final String JWT_AUTHORIZATION = "Authorization";
     private static final String SECRET_KEY = "123456789-123456789-123456789-12";
     private static final long EXPIRE_DAYS = 0;
     private static final long EXPIRE_HOURS = 0;
     private static final long EXPIRE_MINUTES = 2;

     private JwtUtils() {}

    private static Key generateKey() {
         return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(UTF_8));
    }

    private static Date toExpireDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(systemDefault()).toLocalDateTime();
        LocalDateTime endDate = dateTime.plusDays(EXPIRE_DAYS).plusHours(EXPIRE_HOURS).plusMinutes(EXPIRE_MINUTES);

        return Date.from(endDate.atZone(systemDefault()).toInstant());

    }

    public static JwtToken createToken(String email, String role) {
         Date issuedAt = new  Date();
         Date limit = toExpireDate(issuedAt);


         String token = Jwts.builder()
                 .subject(email)
                 .signWith(generateKey())
                 .claim("role", role)
                 .claim("iat", issuedAt)
                 .claim("exp", limit)
                 .compact();

         return new JwtToken(token);

    }

    private static Claims getCalimsFromToken(String token) {
         try {
            return Jwts.parser()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorToken(token))
                    .getBody();
         } catch (JwtException ex) {
             log.error(String.format("Invalid token %s", ex.getMessage()));
         }
         return null;
    }

    private static String refactorToken(String token) {
         if (token.contains(JWT_BEARER)) {
             return token.substring(JWT_BEARER.length());
         }
         return token;

    }

}
