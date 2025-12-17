package com.gerenciamento.infrastructure.dto;

import com.gerenciamento.domain.document.ApiKey;
import lombok.Data;

import java.time.Instant;

@Data
public class ApiKeyDTO {

    private final String id;

    private final String value;

    private final Instant expiresWhen;

    public static ApiKeyDTO create(ApiKey apiKey) {
        return new ApiKeyDTO(
                apiKey.getId(),
                apiKey.getValue(),
                apiKey.getExpiresWhen()
        );
    }
}
