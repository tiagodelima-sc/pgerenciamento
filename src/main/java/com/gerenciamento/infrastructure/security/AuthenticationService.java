package com.gerenciamento.infrastructure.security;

import com.gerenciamento.infrastructure.config.AppConfigProperties;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppConfigProperties properties;
    private final static String AUTH_TOKEN_HEADER_NAME = "x-api-key";

    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);

        if (!Objects.equals(apiKey, properties.getSecurity().getApiKey()))
           throw new BadCredentialsException("API Key inválida: " + apiKey);

        return new ApiKeyAuthenticationToken(apiKey);
    }
}
