package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class InvalidTaskStatusException extends RequestException {

    public InvalidTaskStatusException(String statusStr) {
        super("InvalidTaskStatus", "Status da tarefa é inválida: " + statusStr);
    }
}
