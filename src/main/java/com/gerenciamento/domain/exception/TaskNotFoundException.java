package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class TaskNotFoundException extends RequestException {

    public TaskNotFoundException(String taskId) {
        super("TaskNotFound", "Tarefa nao encontrada: " + taskId);
    }
}
