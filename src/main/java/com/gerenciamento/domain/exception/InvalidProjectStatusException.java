package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class InvalidProjectStatusException extends RequestException {

    public InvalidProjectStatusException(String statusStr) {
        super("InvalidProjectStatus", "Status do projeto inválido: " + statusStr);
    }
}
