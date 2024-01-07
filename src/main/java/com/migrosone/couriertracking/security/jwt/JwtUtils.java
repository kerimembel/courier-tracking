package com.migrosone.couriertracking.security.jwt;


import com.migrosone.couriertracking.security.service.UserDetailsImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Util class to handle JWT operations.
 *
 * @author Kerim Embel
 * @version 0.0.1
 * @since 0.0.1
 */

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        String role = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("userId", userPrincipal.getId());
        if (userPrincipal.getCourierId() != null) {
            claims.put("courierId", userPrincipal.getCourierId());
        }

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plus(jwtExpiration, ChronoUnit.MILLIS)))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {0}", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {0}", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token.");
            log.trace("Expired JWT token trace: {0}", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {0}", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {0}", e);
        }
        return false;
    }

    public String getToken(HttpServletRequest httpServletRequest) {
        final String bearerToken = httpServletRequest.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
