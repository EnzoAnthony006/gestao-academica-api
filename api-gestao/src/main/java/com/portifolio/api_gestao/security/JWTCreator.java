package com.portifolio.api_gestao.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class JWTCreator {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String ROLES_AUTHORITIES = "authorities";

    public static String create(String prefix, String key, JWTObject jwtObject) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        String token = Jwts.builder()
                .setSubject(jwtObject.getSubject())
                .setIssuedAt(jwtObject.getIssuedAt())
                .setExpiration(jwtObject.getExpiration())
                .claim(ROLES_AUTHORITIES, checkRoles(jwtObject.getRoles()))
                .signWith(secretKey)
                .compact();

        return prefix + " " + token;
    }

    public static JWTObject create(String token, String prefix, String key) {
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));

        JWTObject jwtObject = new JWTObject();
        token = token.replace(prefix, "").trim();

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();

        jwtObject.setSubject(claims.getSubject());
        jwtObject.setExpiration(claims.getExpiration());
        jwtObject.setIssuedAt(claims.getIssuedAt());
        jwtObject.setRoles((List) claims.get(ROLES_AUTHORITIES));

        return jwtObject;
    }

    private static List<String> checkRoles(List<String> roles) {
        return roles.stream()
                .map(s -> "ROLE_".concat(s.replaceAll("ROLE_", "")))
                .collect(Collectors.toUnmodifiableList());
    }
}