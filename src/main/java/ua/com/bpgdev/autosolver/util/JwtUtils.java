package ua.com.bpgdev.autosolver.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import ua.com.bpgdev.autosolver.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Component
@EnableConfigurationProperties
public class JwtUtils {

    private final String secretKey;

    public JwtUtils(@Value("${autosolver.security.secret-key}") String secretKey) {
        this.secretKey = secretKey;
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", user.getRoles());
        return createToken(claims, user.getName());
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(DateUtils.addDay(new Date(), 1))
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    public String validateToken(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return extractUsername(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String getClaimValueByName(String usdfAuthToken, String claimName) {
        String claim = null;
        try {
            claim = extractClaim(usdfAuthToken, claims -> claims.get(claimName, String.class));
        } catch (JwtException e) {
            log.error(e.getMessage());
        }
        return claim;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }
}

