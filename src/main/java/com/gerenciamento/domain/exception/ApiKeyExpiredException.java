package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class ApiKeyExpiredException extends RequestException {

    public ApiKeyExpiredException(String apiKeyId) {
        super("ApiKeyNotFound", "API Key expirou!" + apiKeyId);
    }
}
