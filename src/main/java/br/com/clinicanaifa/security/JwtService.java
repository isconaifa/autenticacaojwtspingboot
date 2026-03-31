package br.com.clinicanaifa.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }
    // Gera token só com o email (subject)
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    // Extrai o email do token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrai data de expiração
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrai qualquer claim específica
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Valida se o token é válido e pertence ao usuário
    public boolean isTokenValid(String token, String userDetailsEmail) {
        final String username = extractUsername(token);
        return (username.equals(userDetailsEmail)) && !isTokenExpired(token);
    }

    // Verifica se expirou
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrai todas as claims do token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
