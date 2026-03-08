package com.portifolio.api_gestao.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Value("${security.config.prefix}")
    private String prefix;

    @Value("${security.config.key}")
    private String key;

    @Value("${security.config.expiration}")
    private long expiration;

    public String getPrefix() {
        return prefix;
    }

    public String getKey() {
        return key;
    }

    public long getExpiration() {
        return expiration;
    }
}