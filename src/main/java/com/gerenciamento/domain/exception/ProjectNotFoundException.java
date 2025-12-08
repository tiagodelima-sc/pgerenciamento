package com.gerenciamento.domain.exception;

import com.gerenciamento.infrastructure.exception.RequestException;

public class ProjectNotFoundException extends RequestException {

    public ProjectNotFoundException(String projectId) {
        super("ProjectNotFound", "Projeto nao encontrado: " + projectId);
    }
}
