package com.gerenciamento.domain.applicationservice;

import com.gerenciamento.domain.document.ApiKey;
import com.gerenciamento.domain.exception.ApiKeyExpiredException;
import com.gerenciamento.domain.exception.ApiKeyNotFoundException;
import com.gerenciamento.domain.repository.ApiKeyRepository;
import com.gerenciamento.infrastructure.config.AppConfigProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import static java.time.Instant.EPOCH;

@Service
@RequiredArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepository;
    private final AppConfigProperties properties;

    public ApiKey createApiKey() {
        ApiKey apiKey = ApiKey
                .builder    ()
                .value      (UUID.randomUUID().toString())
                .expiresWhen(
                        OffsetDateTime
                                .now      ()
                                .plusDays (properties.getSecurity().getExpirationDays())
                                .toInstant()
                )
                .build();

        apiKeyRepository.save(apiKey);

        return apiKey;
    }

    @Transactional
    public void revokeApiKey(String id) {
        ApiKey apiKey = loadApiKeyById(id);
        apiKey.setExpiresWhen(EPOCH);
        apiKeyRepository.save(apiKey);
    }

    public void validateApiKey(String apiKey) {
        ApiKey apiKeyObj = loadApiKeyByValue(apiKey);

        if (apiKeyObj.isExpired(Instant.now())) {
            throw new ApiKeyExpiredException(apiKeyObj.getId());
        }
    }

    private ApiKey loadApiKeyById(String id) {
        return apiKeyRepository.findById(id)
                .orElseThrow(() -> new ApiKeyNotFoundException(id));
    }

    private ApiKey loadApiKeyByValue(String apiKey) {
        return apiKeyRepository.findByValue(apiKey)
                .orElseThrow(() -> new ApiKeyNotFoundException(apiKey));
    }
}
