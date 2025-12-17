package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class ApiKeyNotFoundException extends RequestException {

    public ApiKeyNotFoundException(String apiKeyId) {
        super("ApiKeyNotFound", "API Key nao encontrada:" + apiKeyId);
    }
}
