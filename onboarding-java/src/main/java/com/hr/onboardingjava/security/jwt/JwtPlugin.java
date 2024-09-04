package com.hr.onboardingjava.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Component
public class JwtPlugin {

    @Value("${auth.jwt.issuer}")
    private String issuer;

    @Value("${auth.jwt.secret}")
    private String secret;

    @Value("${auth.jwt.accessTokenExpirationHour}")
    private long accessTokenExpirationHour;

    public Optional<Jws<Claims>> validateToken(String jwt) {
        try {
            var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
            var parser = Jwts.parserBuilder().setSigningKey(key).build();
            return Optional.of(parser.parseClaimsJws(jwt));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public String generateAccessToken(String subject, String username, String role) {
        return generateToken(subject, username, role, Duration.ofHours(accessTokenExpirationHour));
    }

    public String generateToken(String subject, String username, String role, Duration expirationPeriod) {
        var claims = Jwts.claims();
        claims.put("role", role);
        claims.put("username", username);

        Instant now = Instant.now();
        var key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expirationPeriod)))
                .addClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}