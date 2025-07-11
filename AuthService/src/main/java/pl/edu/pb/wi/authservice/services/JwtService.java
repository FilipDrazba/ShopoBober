package pl.edu.pb.wi.authservice.services;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.pb.wi.authservice.dtos.JwtValueDto;
import pl.edu.pb.wi.authservice.entities.User;
import pl.edu.pb.wi.authservice.mappers.JwtMapper;

import java.security.Key;
import java.util.Date;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${AUTH_JWT_SECRET_KEY}")
    private String secretKey;

    private final JwtMapper jwtMapper;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secretKey));
    }

    public JwtValueDto generateToken(User user) {
        String tokenValue = Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        return jwtMapper.toJwtDto(tokenValue);
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
